package uk.kulikov.googleauth.signin

import android.content.Context
import android.util.Log
import androidx.credentials.CreatePublicKeyCredentialRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.GetPasswordOption
import androidx.credentials.GetPublicKeyCredentialOption
import androidx.credentials.PasswordCredential
import androidx.credentials.PublicKeyCredential
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.webauthn.PublicKeyCredentialCreationOptions
import androidx.credentials.webauthn.PublicKeyCredentialRequestOptions
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uk.kulikov.googleauth.complete.AuthStateResult
import uk.kulikov.googleauth.core.decompose.DecomposeViewModel
import uk.kulikov.googleauth.utils.BSBApi

private const val WEB_CLIENT_ID =
    "955063396605-n2920r9aj783l7m7dgv5jh2r57ammag6.apps.googleusercontent.com"
private const val TAG = "SignInViewModel"

class SignInViewModel(
    private val context: Context,
    private val onComplete: (AuthStateResult) -> Unit
) : DecomposeViewModel() {
    private val credentialManager = CredentialManager.create(context)

    private val authStateFlow = MutableStateFlow<AuthState>(AuthState.NotStarted)

    fun getState() = authStateFlow.asStateFlow()

    fun onSignIn() {
        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(WEB_CLIENT_ID)
            .build()
        val passwordOption = GetPasswordOption()
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .addCredentialOption(passwordOption)
            .build()

        viewModelScope.launch {
            try {
                val result = credentialManager.getCredential(
                    request = request,
                    context = context,
                )
                handleSignIn(result)
            } catch (e: GetCredentialException) {
                Log.e(TAG, "Failed sign in with google", e)
            }
        }
    }

    private suspend fun handleSignIn(result: GetCredentialResponse) {
        // Handle the successfully returned credential.
        val credential = result.credential

        when (credential) {
            // Passkey credential
            is PublicKeyCredential -> {
                // Share responseJson such as a GetCredentialResponse on your server to
                // validate and authenticate
                val responseJson = credential.authenticationResponseJson

                withContext(Dispatchers.Main) {
                    onComplete(AuthStateResult.PassKey(json = responseJson))
                }
            }

            // Password credential
            is PasswordCredential -> {
                // Send ID and password to your server to validate and authenticate.
                val username = credential.id
                val password = credential.password

                withContext(Dispatchers.Main) {
                    onComplete(
                        AuthStateResult.LoginPassword(
                            login = username,
                            password = password
                        )
                    )
                }
            }

            // GoogleIdToken credential
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        // Use googleIdTokenCredential and extract the ID to validate and
                        // authenticate on your server.
                        val googleIdTokenCredential = GoogleIdTokenCredential
                            .createFrom(credential.data)

                        BSBApi.jwtAuth(googleIdTokenCredential.idToken)

                        withContext(Dispatchers.Main) {
                            onComplete(
                                AuthStateResult.OAuth(
                                    id = googleIdTokenCredential.id,
                                    jwtToken = googleIdTokenCredential.idToken,
                                    displayName = googleIdTokenCredential.displayName
                                )
                            )
                        }
                    } catch (e: GoogleIdTokenParsingException) {
                        Log.e(TAG, "Received an invalid google id token response", e)
                    }
                } else {
                    // Catch any unrecognized custom credential type here.
                    Log.e(TAG, "Unexpected type of credential")
                }
            }

            else -> {
                // Catch any unrecognized credential type here.
                Log.e(TAG, "Unexpected type of credential")
            }
        }
    }
}