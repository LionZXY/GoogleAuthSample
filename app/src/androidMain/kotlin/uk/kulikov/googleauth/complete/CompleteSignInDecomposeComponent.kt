package uk.kulikov.googleauth.complete

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.kulikov.googleauth.core.decompose.DecomposeComponent

class CompleteSignInDecomposeComponent(
    private val result: AuthStateResult
) : DecomposeComponent {
    @Composable
    override fun Render(modifier: Modifier) {
        CompleteSignInComposable(modifier, result)
    }
}