package uk.kulikov.googleauth.complete

import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthStateResult {
    @Serializable
    data class OAuth(
        val id: String,
        val jwtToken: String,
        val displayName: String?
    ) : AuthStateResult

    @Serializable
    data class LoginPassword(val login: String, val password: String) : AuthStateResult

    @Serializable
    data class PassKey(val json: String) : AuthStateResult
}