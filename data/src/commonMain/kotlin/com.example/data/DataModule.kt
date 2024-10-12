package com.example.data

import com.example.data.repository.CategoryRepository
import com.example.data.repository.LoginRepository
import org.example.marketaja.di.InstancesManager

object DataModule {

    fun init() = with(InstancesManager) {
        install { LoginRepository(it.get(), it.get()) }
        install { CategoryRepository(it.get()) }
    }
}