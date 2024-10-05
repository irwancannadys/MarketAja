package org.example.marketaja.service

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.logging.Logger
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.Json
import org.example.marketaja.ValueStoreManager

class NetworkClient(
    private val baseUrl: String,
    private val valueStoreManager: ValueStoreManager
) {
    private val client by lazy {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                    prettyPrint = true
                })
            }
        }
    }

    suspend fun get(
        path: String,
        parameter: Map<String, String>? = null
    ): HttpResponse {
        return client.post(baseUrl) {
            url {
                appendPathSegments(path)
                parameter?.forEach {
                    parameters.append(it.key, it.value)
                }
                bearerAuth(valueStoreManager.token)
            }
            contentType(ContentType.Application.Json)
        }
    }

    suspend fun post(
        path: String,
        parameter: Map<String, String>? = null,
        body: Any? = null
    ): HttpResponse {
        return client.post(baseUrl) {
            url {
                appendPathSegments(path)
                parameter?.forEach {
                    parameters.append(it.key, it.value)
                }
                bearerAuth(valueStoreManager.token)
            }
            contentType(ContentType.Application.Json)
            setBody(body)
        }
    }
}