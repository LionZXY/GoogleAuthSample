package uk.kulikov.googleauth.root

import kotlinx.serialization.Serializable
import uk.kulikov.googleauth.complete.AuthStateResult

@Serializable
sealed class RootScreenConfig {
    data object SignIn : RootScreenConfig()

    data class CompleteSignIn(val result: AuthStateResult) : RootScreenConfig()
}