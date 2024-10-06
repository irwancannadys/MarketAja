package com.example.core.service

import com.example.core.local.ValueStoreManager
import com.example.core.service.NetworkClient
import org.example.marketaja.di.InstancesManager

object ServiceModule {

    fun init() = with(InstancesManager) {
        install {
            val BASE_URL = "https://marketfake.fly.dev/"
            NetworkClient(baseUrl = BASE_URL, it.get())
        }

        install { ValueStoreManager() }
    }
}