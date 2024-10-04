package uk.kulikov.googleauth.root

import kotlinx.serialization.Serializable

@Serializable
sealed class RootScreenConfig {
    data object SignIn : RootScreenConfig()
}