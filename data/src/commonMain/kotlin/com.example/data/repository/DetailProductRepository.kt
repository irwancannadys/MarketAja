package com.example.data.repository

import com.example.core.service.NetworkAsyncState
import com.example.core.service.NetworkClient
import com.example.core.service.NetworkRepository
import com.example.data.response.DetailProductResponse
import kotlinx.coroutines.flow.Flow

class DetailProductRepository(
    private val networkClient: NetworkClient
) : NetworkRepository() {

    fun getDetailProduct(id: Int): Flow<NetworkAsyncState<DetailProductResponse.Data>> {
        return suspend {
            networkClient.get(
                path = "product/$id",
            )
        }.reduce<DetailProductResponse, DetailProductResponse.Data> { response ->
            val data = response.data
            if (data != null) {
                NetworkAsyncState.Success(data)
            } else {
                NetworkAsyncState.Failure(Throwable("data is null guys"))
            }
        }
    }
}