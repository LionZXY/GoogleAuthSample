package uk.kulikov.googleauth.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import googleauthsample.app.generated.resources.Res
import googleauthsample.app.generated.resources.pic_busycloud
import org.jetbrains.compose.resources.painterResource

@Composable
fun SignInComposable(
    modifier: Modifier, authState: AuthState, signInGoogle: () -> Unit
) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (authState) {
            is AuthState.NotStarted -> SignInScreen(signInGoogle)

            AuthState.LoginInProgress -> CircularProgressIndicator()
        }
    }
}

@Composable
private fun SignInScreen(signInGoogle: () -> Unit) {
    Image(
        modifier = Modifier.size(128.dp),
        painter = painterResource(Res.drawable.pic_busycloud),
        contentDescription = null
    )

    Button(onClick = signInGoogle) {
        Text("Sign in with Google")
    }
}