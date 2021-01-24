package com.example.domain.base

import com.example.domain.model.Resource
import com.example.domain.usecase.DispatchersUseCase
import kotlinx.coroutines.withContext

abstract class UseCase<Type>(private val dispatcher: DispatchersUseCase) where Type : Any {
    abstract suspend fun run(): Type
    suspend operator fun invoke(): Resource<Type> {
        return withContext(dispatcher.ioDispatcher) {
            try {
                Resource.Success(run())
            } catch (exception: Exception) {
                Resource.Error(exception)
            }
        }
    }
}
