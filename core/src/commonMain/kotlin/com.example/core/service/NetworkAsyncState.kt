package com.example.core.service

import androidx.compose.runtime.Composable

sealed class NetworkAsyncState<out T> {
    data object Idle: NetworkAsyncState<Nothing>()
    data object Loading: NetworkAsyncState<Nothing>()
    data class Success<T>(val data: T): NetworkAsyncState<T>()
    data class Failure(val throwable: Throwable): NetworkAsyncState<Nothing>()

}

fun <T> NetworkAsyncState<T>.isLoading(): Boolean {
    return this is NetworkAsyncState.Loading
}

fun <T> NetworkAsyncState<T>.isIdle(): Boolean {
    return this is NetworkAsyncState.Idle
}

fun <T> NetworkAsyncState<T>.getOrNull(): T? {
    return if (this is NetworkAsyncState.Success) {
        data
    } else {
        null
    }
}

@Composable
fun <T> NetworkAsyncState<T>.onLoading(content: @Composable () -> Unit = {}) {
    if (this is NetworkAsyncState.Loading) content.invoke()
}

@Composable
fun <T> NetworkAsyncState<T>.onIdle(content: @Composable () -> Unit = {}) {
    if (this is NetworkAsyncState.Idle) content.invoke()
}

@Composable
fun <T> NetworkAsyncState<T>.onSuccess(content: @Composable (T) -> Unit = {}) {
    if (this is NetworkAsyncState.Success) content.invoke(data)
}

@Composable
fun <T> NetworkAsyncState<T>.onFailure(content: @Composable (Throwable) -> Unit = {}) {
    if (this is NetworkAsyncState.Failure) content.invoke(throwable)
}