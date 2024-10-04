package uk.kulikov.googleauth.signin

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import uk.kulikov.googleauth.core.decompose.DecomposeComponent

class SignInDecomposeComponent(componentContext: ComponentContext) : DecomposeComponent {
    @Composable
    override fun Render(modifier: Modifier) {
        SignInComposable(modifier)
    }
}