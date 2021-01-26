package com.dog.app.util

interface ViewEventListener<T> {
    fun onViewEvent(viewEvents: T)
}