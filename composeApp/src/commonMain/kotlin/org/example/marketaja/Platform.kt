package org.example.marketaja

import androidx.compose.runtime.Composable

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun getStatus()
//
//@Composable
//expect fun OnBackPress(enable: Boolean, block: () -> Unit)