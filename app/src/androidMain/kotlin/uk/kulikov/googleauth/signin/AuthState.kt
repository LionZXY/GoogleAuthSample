package uk.kulikov.googleauth.signin

sealed interface AuthState {
    data object NotStarted : AuthState

    data object LoginInProgress : AuthState
}