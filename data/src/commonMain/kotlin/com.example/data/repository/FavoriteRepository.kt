package com.example.data.repository

import com.example.core.local.ValueStoreManager
import com.example.data.response.ProductListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

class FavoriteRepository(
    private val valueStoreManager: ValueStoreManager
) {

    fun addToFavorite(dataFavorite: String) : Flow<String> {
        return flow {
            valueStoreManager.listFavorite = dataFavorite
            emit(dataFavorite)
            println("data list nya: $dataFavorite")
        }
    }

    fun getFavorite() : Flow<List<ProductListResponse.Data>>{
        return flow {
            val listOfJsonFavorite = valueStoreManager.listFavorite
            print("datanyanihh: $listOfJsonFavorite")
            if (listOfJsonFavorite.isNotEmpty()) {
                val listFavorite : List<ProductListResponse.Data> = Json.decodeFromString(listOfJsonFavorite)
                emit(listFavorite)
            } else {
                emit(listOf())
            }
        }
    }
}