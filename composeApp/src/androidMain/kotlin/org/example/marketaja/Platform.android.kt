package org.example.marketaja

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun exitApp() {
    val context = AppContextProvider.getContext()

    (context as MainActivity).finish()
}