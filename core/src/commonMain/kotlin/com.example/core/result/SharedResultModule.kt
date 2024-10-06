package com.example.core.result

import com.example.core.result.SharedResultManager
import org.example.marketaja.di.InstancesManager

object SharedResultModule {

    fun init() = with(InstancesManager) {
        install { SharedResultManager() }
    }
}