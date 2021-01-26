package com.dog.domain.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

data class AppDispatchers(
    val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
)
