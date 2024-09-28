package org.example.marketaja.service

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.path
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.Json


//const val BASE_URL = "https://...."
//abstract class KtorService {
//
//    val client = HttpClient {
//        install(ContentNegotiation) {
//            json(Json {
//                ignoreUnknownKeys = true
//                useAlternativeNames = false
//                prettyPrint = true
//            })
//        }
//    }
//
//    fun HttpRequestBuilder.pathUrl(endPoint: String) {
//        url {
//            takeFrom(BASE_URL)
//            path(endPoint)
//        }
//    }
//}

class NetworkBuilder {
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

    suspend fun get(path: String): HttpResponse {
        val url = "$BASE_URLS$path"
        return client.get(url)
    }

    companion object {
        internal const val BASE_URLS = "https://marketfake.fly.dev/"
    }
}