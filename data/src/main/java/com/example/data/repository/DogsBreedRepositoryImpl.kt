package com.example.data.repository

import com.example.data.api.DogsApi
import com.example.data.db.DogTypeDao
import com.example.data.model.local.DogTypeEntity
import com.example.data.model.remote.DogImagesDto
import com.example.data.model.remote.DogsBreedResponseDto
import java.lang.NullPointerException

internal class DogsBreedRepositoryImpl(
    private val dogsApi: DogsApi,
    private val dogTypeDao: DogTypeDao
) : DogsBreedRepository {

    override suspend fun getDogTypeFromRemote(): DogsBreedResponseDto {
        return dogsApi.getListOfAllBreeds()
    }

    override suspend fun getDogTypeFromDb(): List<DogTypeEntity> {
        return dogTypeDao.getAll().ifEmpty {
            throw NullPointerException()
        }
    }

    override suspend fun persistIntoDb(dogsType: List<DogTypeEntity>) {
        dogTypeDao.insertAll(dogsType)
    }

    override suspend fun getDogBreedTypePhotos(breed: String): DogImagesDto {
        return dogsApi.getImagesByBreed(breed)
    }

    override suspend fun getDogSubBreedTypePhotos(breed: String, subBreed: String): DogImagesDto {
        return dogsApi.getImagesBySubBreed(breed, subBreed)
    }
}