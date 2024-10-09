package uk.kulikov.googleauth.signin

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import uk.kulikov.googleauth.core.decompose.DecomposeComponent
import uk.kulikov.googleauth.core.decompose.viewModelWithFactoryWithoutRemember

class SignInDecomposeComponent(
    componentContext: ComponentContext,
    context: Context
) : DecomposeComponent, ComponentContext by componentContext {
    private val viewModel = viewModelWithFactoryWithoutRemember(context) {
        SignInViewModel(context)
    }

    @Composable
    override fun Render(modifier: Modifier) {
        val authState by viewModel.getState().collectAsState()
        SignInComposable(
            modifier,
            authState = authState,
            signInGoogle = viewModel::onSignIn
        )
    }
}