package com.example.data.repository

import com.example.core.service.NetworkAsyncState
import com.example.core.service.NetworkClient
import com.example.core.service.NetworkRepository
import com.example.data.response.ProductListResponse
import kotlinx.coroutines.flow.Flow

class ProductListRepository(
    private val networkClient: NetworkClient
) : NetworkRepository() {

    fun getProductList(
        categoryId: String
    ): Flow<NetworkAsyncState<List<ProductListResponse.Data>>> {
        return suspend {
            val parameter = mutableMapOf<String, String>()
                .also {
                    if (categoryId.isNotEmpty()) {
                        it["categoryId"] = categoryId
                    }else {
                        throw IllegalArgumentException("Operator tidak ditemukan!")
                    }
                }
            networkClient.get(
                path = "product",
                parameter = parameter
            )
        }.reduce<ProductListResponse, List<ProductListResponse.Data>> { response ->
            val data = response.data
            if (data.isNullOrEmpty()) {
                NetworkAsyncState.Failure(Throwable("data is null guys"))
            } else {
                NetworkAsyncState.Success(data)
            }
        }
    }
}