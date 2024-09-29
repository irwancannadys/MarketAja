package org.example.marketaja

import org.example.marketaja.di.InstancesManager
import org.example.marketaja.login.LoginViewModel

object LoginModule {

    fun init() = with(InstancesManager) {
        install {
            LoginViewModel(it.get())
        }
    }
}