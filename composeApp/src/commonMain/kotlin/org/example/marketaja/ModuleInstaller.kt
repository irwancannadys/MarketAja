package org.example.marketaja

import com.example.core.result.SharedResultModule
import com.example.core.service.ServiceModule
import com.example.data.DataModule

object ModuleInstaller {

    fun install() {
        LoginModule.init()
        DataModule.init()
        ServiceModule.init()
        SharedResultModule.init()
    }
}