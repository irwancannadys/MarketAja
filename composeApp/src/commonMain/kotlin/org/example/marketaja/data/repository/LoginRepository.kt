package org.example.marketaja.data.repository

import kotlinx.coroutines.flow.Flow
import org.example.marketaja.ValueStoreManager
import org.example.marketaja.data.request.LoginRequest
import org.example.marketaja.data.response.LoginResponse
import org.example.marketaja.service.NetworkAsyncState
import org.example.marketaja.service.NetworkClient
import org.example.marketaja.service.NetworkRepository

class LoginRepository(
    private val networkClient: NetworkClient,
    private val valueStoreManager: ValueStoreManager
) : NetworkRepository(){

    fun login(username: String, password: String): Flow<NetworkAsyncState<String>> {
        val request = LoginRequest(username, password)
        return suspend {
            networkClient.post(
                path = "",
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