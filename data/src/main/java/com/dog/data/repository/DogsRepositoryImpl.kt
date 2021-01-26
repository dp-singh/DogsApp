package com.dog.data.repository

import com.dog.data.api.DogsApi
import com.dog.data.db.DogsDao
import com.dog.data.model.local.DogEntity
import com.dog.data.model.remote.DogsResponseDto

class DogsRepositoryImpl(
    private val dogsApi: DogsApi,
    private val dogsDao: DogsDao
) : DogsRepository {

    override suspend fun getDogListFromNetwork(): DogsResponseDto {
        return dogsApi.getListOfAllBreeds()
    }

    override suspend fun getDogTypeFromDb(): List<DogEntity> {
        return dogsDao.getAll().ifEmpty {
            throw NullPointerException()
        }
    }

    override suspend fun persistIntoDb(dogsTypeEntity: List<DogEntity>) {
        dogsDao.insertAll(dogsTypeEntity)
    }
}