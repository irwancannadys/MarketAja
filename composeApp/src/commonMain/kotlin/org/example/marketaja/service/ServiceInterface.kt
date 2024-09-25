package org.example.marketaja.service

import io.ktor.client.call.body
import io.ktor.client.request.get

class ServiceInterface : KtorService() {

    suspend fun getData() : Any = client.get {
        pathUrl("admin/user")
    }.body()

}