package uk.kulikov.googleauth.core.theme

import kotlinx.coroutines.flow.MutableStateFlow

object DarkModeSingleton {
    val darkMode = MutableStateFlow(false)
    val devMode = MutableStateFlow(false)
}