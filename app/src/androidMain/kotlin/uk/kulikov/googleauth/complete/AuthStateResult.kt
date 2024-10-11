package uk.kulikov.googleauth.complete

import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthStateResult {
    data class OAuth(
        val id: String,
        val jwtToken: String,
        val displayName: String?
    ) : AuthStateResult

    data class LoginPassword(val login: String, val password: String) : AuthStateResult

    data class PassKey(val json: String) : AuthStateResult
}