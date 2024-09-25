package org.example.marketaja.data

import org.example.marketaja.service.ServiceInterface

class RemoteDataSourceImpl(
    private val service : ServiceInterface
) : RemoteDataSource {

    override suspend fun getData(): Any {
        return service.getData()
    }
}