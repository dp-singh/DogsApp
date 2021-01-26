package com.dog.domain.model

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class SuccessFromCache<out T>(val data: T, val throwable: Throwable) : Resource<T>()
    data class Error(val throwable: Throwable) : Resource<Nothing>()
}