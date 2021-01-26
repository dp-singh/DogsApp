package com.dog.domain.usecase

import com.dog.data.repository.DogsRepository
import com.dog.domain.mapper.dog.DogsFromDbToDataMapper
import com.dog.domain.mapper.dog.DogsFromDataToDbMapper
import com.dog.domain.mapper.dog.DogsFromNetworkToDataMapper
import com.dog.domain.model.Dog
import com.dog.domain.model.Resource
import com.dog.domain.utils.AppDispatchers
import kotlinx.coroutines.withContext

class GetDogListUseCase(
    private val dogsRepository: DogsRepository,
    private val networkToDataMapper: DogsFromNetworkToDataMapper,
    private val dbToDataMapper: DogsFromDbToDataMapper,
    private val dataToDbMapper: DogsFromDataToDbMapper,
    private val dispatcher: AppDispatchers
) {

    private suspend fun getFromRemoteAndPersist(): Resource<List<Dog>> {
        return withContext(dispatcher.ioDispatcher) {
            try {
                val result = networkToDataMapper.map(dogsRepository.getDogListFromNetwork())
                dogsRepository.persistIntoDb(dataToDbMapper.map(result))
                Resource.Success(result)
            } catch (exception: Exception) {
                Resource.Error(exception)
            }
        }
    }

    private suspend fun getFromPersistence(parentThrowable: Throwable): Resource<List<Dog>> {
        return withContext(dispatcher.ioDispatcher) {
            try {
                val result = dogsRepository.getDogTypeFromDb()
                Resource.SuccessFromCache(dbToDataMapper.map(result), parentThrowable)
            } catch (exception: Exception) {
                Resource.Error(parentThrowable)
            }
        }
    }

    suspend operator fun invoke(): Resource<List<Dog>> {
        val result = getFromRemoteAndPersist()
        return if (result is Resource.Error) {
            getFromPersistence(result.throwable)
        } else {
            result
        }
    }
}