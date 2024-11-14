package uk.kulikov.googleauth.utils

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

private val httpClient = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
        )
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Log.i("BSBApi", message)
            }
        }
        level = LogLevel.ALL
    }

    install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }
}

@Serializable
data class BSBOneTapGoogle(
    @SerialName("jwt_token")
    val token: String
)

object BSBApi {
    suspend fun jwtAuth(token: String) = withContext(Dispatchers.Default) {
        val response = httpClient.post {
            url("https://cloud.dev.busy.bar/api/v0/oauth2/google/one-tap")
            setBody(BSBOneTapGoogle(token))
        }
        Log.i("BSBApi", response.toString())
    }
}