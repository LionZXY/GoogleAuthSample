package uk.kulikov.googleauth.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import googleauthsample.app.generated.resources.Res
import googleauthsample.app.generated.resources.pic_busycloud
import org.jetbrains.compose.resources.painterResource

@Composable
fun SignInComposable(modifier: Modifier) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(128.dp),
            painter = painterResource(Res.drawable.pic_busycloud),
            contentDescription = null
        )
    }
}