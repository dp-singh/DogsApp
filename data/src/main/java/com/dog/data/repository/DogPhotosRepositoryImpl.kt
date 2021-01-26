package com.dog.data.repository

import com.dog.data.api.DogPhotosApi
import com.dog.data.db.DogPhotosDao
import com.dog.data.model.local.DogPhotosEntity
import com.dog.data.model.remote.DogPhotosResponseDto
import java.lang.NullPointerException

class DogPhotosRepositoryImpl(
    private val dogsPhotosApi: DogPhotosApi,
    private val dogPhotosDao: DogPhotosDao
) : DogPhotosRepository {

    override suspend fun getByBreed(breed: String): DogPhotosResponseDto {
        return dogsPhotosApi.getImagesByBreed(breed)
    }

    override suspend fun getByBreedAndSubBreed(
        breed: String,
        subBreed: String
    ): DogPhotosResponseDto {
        return dogsPhotosApi.getImagesBySubBreed(breed, subBreed)
    }

    override suspend fun getDogPhotosFromDb(
        breed: String,
        subBreed: String
    ): List<DogPhotosEntity> {
        return dogPhotosDao.getByBreedAndSubBreed(breed, subBreed).ifEmpty {
            throw NullPointerException()
        }
    }

    override suspend fun storeItIntoDb(dogPhotosEntityList: List<DogPhotosEntity>) {
        dogPhotosDao.insertAll(dogPhotosEntityList)
    }
}
