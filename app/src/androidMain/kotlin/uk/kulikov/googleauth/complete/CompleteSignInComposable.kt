package uk.kulikov.googleauth.complete

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import googleauthsample.app.generated.resources.Res
import googleauthsample.app.generated.resources.pic_busycloud
import org.jetbrains.compose.resources.painterResource
import uk.kulikov.googleauth.core.theme.LocalBusyBarFonts
import uk.kulikov.googleauth.core.theme.LocalPallet
import uk.kulikov.googleauth.signin.AuthState


@Composable
fun CompleteSignInComposable(
    modifier: Modifier, authState: AuthStateResult
) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val text = when (authState) {
            is AuthStateResult.LoginPassword -> """
                Login and password
                Login: ${authState.login}
                Password: ${authState.password}
            """.trimIndent()

            is AuthStateResult.OAuth -> """
                OAuth Google
                Id: ${authState.id}
                Name: ${authState.displayName}
                Token: ${authState.jwtToken}
            """.trimIndent()

            is AuthStateResult.PassKey -> """
                PassKey
                ${authState.json}
            """.trimIndent()
        }

        SelectableTextField(text)
    }
}

@Composable
private fun SelectableTextField(text: String = "test") {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .verticalScroll(rememberScrollState())
            .background(LocalPallet.current.neutral.tertiary)
    ) {
        SelectionContainer {
            Text(
                modifier = Modifier.padding(8.dp),
                text = text,
                fontFamily = LocalBusyBarFonts.current.jetbrainsMono
            )
        }
    }
}