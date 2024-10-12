package org.example.marketaja.login

import org.example.marketaja.di.InstancesManager

object LoginModule {

    fun init() = with(InstancesManager) {
        install {
            LoginViewModel(it.get())
        }
    }
}