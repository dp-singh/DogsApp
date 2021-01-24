package com.example.domain.usecase

import com.example.data.repository.DogsBreedRepository
import com.example.domain.mapper.DogsBreedEntityToDbMapper
import com.example.domain.mapper.DogsBreedMapper
import com.example.domain.model.DogType
import com.example.domain.model.Resource
import kotlinx.coroutines.withContext

class GetDogBreedsUseCase(
    private val dogsBreedRepository: DogsBreedRepository,
    private val dogsBreedMapper: DogsBreedMapper,
    private val dogsBreedEntityToDbMapper: DogsBreedEntityToDbMapper,
    private val dispatcher: DispatchersUseCase
) {

    private suspend fun readFromRemoteAndPersist(): Resource<List<DogType>> {
        return withContext(dispatcher.ioDispatcher) {
            try {
                val result = dogsBreedMapper.toEntity(dogsBreedRepository.getDogTypeFromRemote())
                dogsBreedRepository.persistIntoDb(dogsBreedEntityToDbMapper.toDto(result))
                Resource.Success(result)
            } catch (exception: Exception) {
                Resource.Error(exception)
            }
        }
    }

    private suspend fun readFromPersistence(parentThrowable: Throwable): Resource<List<DogType>> {
        return withContext(dispatcher.ioDispatcher) {
            try {
                val result = dogsBreedRepository.getDogTypeFromDb()
                Resource.Success(dogsBreedEntityToDbMapper.toEntity(result))
            } catch (exception: Exception) {
                Resource.Error(parentThrowable)
            }
        }
    }

    suspend operator fun invoke(): Resource<List<DogType>> {
        val result = readFromRemoteAndPersist()
        return if (result is Resource.Error) {
            readFromPersistence(result.throwable)
        } else {
            result
        }
    }
}