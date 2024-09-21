package org.example.marketaja

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import org.example.marketaja.home.HomeScreen
import org.example.marketaja.playground.PlaygroundScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
//        PlaygroundScreen()
        HomeScreen()
    }
}

//repository :
//        1. playground
//            (tugas, coret2an, uji coba, dll)
//branch  = main
//            - pertmuan 1
//            - pertmuan 2
//
//        2. real project nya
//        yang udah rapih jadi ga berantakan untuk struktur dan lain nya