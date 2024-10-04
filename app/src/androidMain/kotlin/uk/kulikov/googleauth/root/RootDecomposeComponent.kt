package uk.kulikov.googleauth.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import uk.kulikov.googleauth.core.decompose.DecomposeComponent
import uk.kulikov.googleauth.signin.SignInDecomposeComponent

class RootDecomposeComponent(
    componentContext: ComponentContext
) : DecomposeComponent, ComponentContext by componentContext {
    protected val navigation = StackNavigation<RootScreenConfig>()

    private val stack: Value<ChildStack<RootScreenConfig, DecomposeComponent>> = childStack(
        source = navigation,
        serializer = RootScreenConfig.serializer(),
        initialConfiguration = RootScreenConfig.SignIn,
        handleBackButton = true,
        childFactory = ::child,
    )


    @Composable
    override fun Render(modifier: Modifier) {
        val childStack by stack.subscribeAsState()

        Children(
            stack = childStack,
        ) {
            it.instance.Render(modifier)
        }
    }

    private fun child(
        config: RootScreenConfig,
        componentContext: ComponentContext
    ) = when (config) {
        RootScreenConfig.SignIn -> SignInDecomposeComponent(componentContext)
    }
}