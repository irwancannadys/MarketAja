package org.example.marketaja.service

sealed class NetworkAsyncState<out T> {
    data object Idle: NetworkAsyncState<Nothing>()
    data object Loading: NetworkAsyncState<Nothing>()
    data class Success<T>(val data: T): NetworkAsyncState<T>()
    data class Failure(val throwable: Throwable): NetworkAsyncState<Nothing>()

}