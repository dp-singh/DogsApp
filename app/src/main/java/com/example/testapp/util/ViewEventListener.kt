package com.example.testapp.util

interface ViewEventListener<T> {
    fun onViewEvent(viewEvents: T)
}