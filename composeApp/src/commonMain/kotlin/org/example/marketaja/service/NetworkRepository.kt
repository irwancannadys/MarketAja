package org.example.marketaja.service

import io.ktor.client.call.body
import kotlinx.coroutines.flow.Flow
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

abstract class NetworkRepository {

    inline fun <reified T> (suspend () -> HttpResponse).reduce(): Flow<NetworkAsyncState<T>> {
        return flow {
            val httpResponse = invoke()
            if (httpResponse.status.isSuccess()) {
                val data = httpResponse.body<T>()
                val async = NetworkAsyncState.Success(data)
                emit(async)
            } else {
                val message = httpResponse.bodyAsText()
                val throwable = Throwable(message)
                val async = NetworkAsyncState.Failure(throwable)
                emit(async)
            }
        }.onStart {
            emit(NetworkAsyncState.Loading)
        }.catch {
            val async = NetworkAsyncState.Failure(it)
            it.printStackTrace()
            emit(async)
        }
    }
}