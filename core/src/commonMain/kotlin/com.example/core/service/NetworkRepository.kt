package com.example.core.service

import io.ktor.client.call.body
import kotlinx.coroutines.flow.Flow
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.DefaultJson
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.example.marketaja.data.exception.InternalException
import com.example.core.exception.NetworkException

abstract class NetworkRepository {

    protected inline fun <reified T, U>(suspend () -> HttpResponse).reduce(
        crossinline block: (T) -> NetworkAsyncState<U>
    ) : Flow<NetworkAsyncState<U>> {
        return flow {
            val httpResponse = invoke()
            if (httpResponse.status.isSuccess()) {
                val data = httpResponse.body<T>()
                val dataState = block.invoke(data)
                emit(dataState)
            } else {
                val message = try {
                    val json = DefaultJson
                        .parseToJsonElement(httpResponse.bodyAsText())
                        .jsonObject
                    json["message"]?.jsonPrimitive?.content
                } catch (e: Throwable) {
                    e.printStackTrace()
                    httpResponse.bodyAsText()
                }
                val throwable = InternalException(message)
                val state = NetworkAsyncState.Failure(throwable)
                emit(state)
            }
        }.onStart {
            emit(NetworkAsyncState.Loading)
        }.catch {
            val state = NetworkAsyncState.Failure(NetworkException(it.message))
            emit(state)
        }
    }
}