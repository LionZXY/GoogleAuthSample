package uk.kulikov.googleauth.root

import kotlinx.serialization.Serializable
import uk.kulikov.googleauth.complete.AuthStateResult

@Serializable
sealed class RootScreenConfig {
    @Serializable
    data object SignIn : RootScreenConfig()

    @Serializable
    data class CompleteSignIn(val result: AuthStateResult) : RootScreenConfig()
}