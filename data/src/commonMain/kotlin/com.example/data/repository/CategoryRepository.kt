package com.example.data.repository

import com.example.core.service.NetworkAsyncState
import com.example.core.service.NetworkClient
import com.example.core.service.NetworkRepository
import com.example.data.response.CategoryResponse
import kotlinx.coroutines.flow.Flow

class CategoryRepository(
    private val networkClient: NetworkClient
) : NetworkRepository() {

    fun getCategory(): Flow<NetworkAsyncState<List<CategoryResponse.Data>>> {
        return suspend {
            networkClient.get(
                path = "product/category",
            )
        }.reduce<CategoryResponse, List<CategoryResponse.Data>> { response ->
            val data = response.data
            if (data.isNullOrEmpty()) {
                NetworkAsyncState.Failure(Throwable("data is null guys"))
            } else {
                NetworkAsyncState.Success(data)
            }
        }
    }
}