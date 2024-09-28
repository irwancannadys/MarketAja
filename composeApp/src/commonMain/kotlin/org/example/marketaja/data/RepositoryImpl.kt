package org.example.marketaja.data

import kotlinx.coroutines.flow.Flow
import org.example.marketaja.data.response.UserResponse
import org.example.marketaja.service.NetworkAsyncState
import org.example.marketaja.service.NetworkBuilder
import org.example.marketaja.service.NetworkRepository

class RepositoryImpl(
    private val service : NetworkBuilder
) : NetworkRepository() {

//    override fun getData(): Flow<NetworkAsyncState<UserResponse>> {
//        return suspend {
//            service.get("")
//        }.reduce(UserResponse)
//    }

    fun getUser(): Flow<NetworkAsyncState<UserResponse>> {
        return suspend {
            service.get("")
        }.reduce<UserResponse>()
    }
}