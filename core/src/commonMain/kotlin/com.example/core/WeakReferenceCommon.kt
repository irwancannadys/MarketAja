package com.example.core


expect class WeakReferenceCommon<T : Any>(referred: T) {
    fun get(): T?
    fun clear()
}