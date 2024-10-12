package org.example.marketaja

import com.example.core.result.SharedResultModule
import com.example.core.service.ServiceModule
import com.example.data.DataModule
import org.example.marketaja.home.HomeModule
import org.example.marketaja.login.LoginModule

object ModuleInstaller {

    fun install() {
        LoginModule.init()
        HomeModule.init()
        DataModule.init()
        ServiceModule.init()
        SharedResultModule.init()
    }
}