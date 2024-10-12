package com.example.core.utils

fun Double.toRupiah(): String {
    return "Rp" + this.toLong().toString()
        .reversed()
        .chunked(3)
        .joinToString(".")
        .reversed()
}