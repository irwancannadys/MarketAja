package org.example.marketaja.home

import org.example.marketaja.di.InstancesManager
import org.example.marketaja.login.LoginViewModel

object HomeModule {

    fun init() = with(InstancesManager) {
        install {
            HomeViewModel(it.get())
        }
    }
}