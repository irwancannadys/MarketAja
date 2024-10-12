package org.example.marketaja.product

import org.example.marketaja.di.InstancesManager
import org.example.marketaja.home.HomeViewModel

object ProductModule {
    fun init() = with(InstancesManager) {
        install {
            ProductViewModel(it.get())
        }
    }
}