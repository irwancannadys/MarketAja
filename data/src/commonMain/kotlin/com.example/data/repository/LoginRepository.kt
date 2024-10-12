package com.example.data.repository

import com.example.core.local.ValueStoreManager
import kotlinx.coroutines.flow.Flow
import com.example.data.request.LoginRequest
import org.example.marketaja.data.response.LoginResponse
import com.example.core.service.NetworkAsyncState
import com.example.core.service.NetworkClient
import com.example.core.service.NetworkRepository

class LoginRepository(
    private val networkClient: NetworkClient,
    private val valueStoreManager: ValueStoreManager
) : NetworkRepository(){

    fun login(username: String, password: String): Flow<NetworkAsyncState<String>> {
        val request = LoginRequest(username, password)
        return suspend {
            networkClient.post(
                path = "auth/login",
                body = request
            )
        }.reduce<LoginResponse, String> { response ->
            val token = response.data?.token
            if (token.isNullOrEmpty()) {
                NetworkAsyncState.Failure(Throwable("token nya kosong yah...."))
            } else {
                valueStoreManager.token = token
                NetworkAsyncState.Success(token)
            }
        }
    }
}