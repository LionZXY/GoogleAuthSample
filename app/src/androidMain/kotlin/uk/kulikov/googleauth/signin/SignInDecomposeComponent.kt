package uk.kulikov.googleauth.signin

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pushNew
import uk.kulikov.googleauth.core.decompose.DecomposeComponent
import uk.kulikov.googleauth.core.decompose.viewModelWithFactoryWithoutRemember
import uk.kulikov.googleauth.root.RootScreenConfig

class SignInDecomposeComponent(
    componentContext: ComponentContext,
    context: Context,
    navigation: StackNavigation<RootScreenConfig>
) : DecomposeComponent, ComponentContext by componentContext {
    private val viewModel = viewModelWithFactoryWithoutRemember(navigation) {
        SignInViewModel(
            context,
            onComplete = { navigation.pushNew(RootScreenConfig.CompleteSignIn(it)) }
        )
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