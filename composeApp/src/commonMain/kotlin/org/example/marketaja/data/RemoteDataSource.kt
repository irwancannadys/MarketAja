package org.example.marketaja.data

interface RemoteDataSource {

    suspend fun getData() : Any
}