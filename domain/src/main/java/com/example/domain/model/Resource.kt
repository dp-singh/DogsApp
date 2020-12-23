package com.example.domain.model

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val throwable: Throwable) : Resource<Nothing>()
}