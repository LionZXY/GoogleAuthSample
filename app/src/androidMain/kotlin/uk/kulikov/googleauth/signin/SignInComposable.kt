package uk.kulikov.googleauth.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import googleauthsample.app.generated.resources.Res
import googleauthsample.app.generated.resources.pic_busycloud
import org.jetbrains.compose.resources.painterResource

@Composable
fun SignInComposable() {
    Column {
        Image(
            painter = painterResource(Res.drawable.pic_busycloud),
            contentDescription = null
        )
    }
}