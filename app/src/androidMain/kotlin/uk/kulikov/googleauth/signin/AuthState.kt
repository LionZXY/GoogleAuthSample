package uk.kulikov.googleauth.signin

sealed interface AuthState {
    data object NotStarted : AuthState

    data class OAuth(
        val id: String,
        val jwtToken: String,
        val displayName: String?
    ) : AuthState

    data class LoginPassword(val login: String, val password: String) : AuthState

    data class PassKey(val json: String) : AuthState
}