package com.example.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

data class DispatchersUseCase(
    val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
)
