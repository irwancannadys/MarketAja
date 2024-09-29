package org.example.marketaja


expect class WeakReferenceCommon<T : Any>(referred: T) {
    fun get(): T?
    fun clear()
}