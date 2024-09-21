package org.example.marketaja

import android.app.Application
import android.content.Context

actual object AppContextProvider {

    private lateinit var mainActivity: MainActivity

    fun setActivity(mainActivity: MainActivity) {
        this.mainActivity = mainActivity
    }

    fun getContext(): Context {
        return mainActivity
    }


}