package com.dog.data.repository

import com.dog.data.model.local.DogPhotosEntity
import com.dog.data.model.remote.DogPhotosResponseDto

interface DogPhotosRepository {
    suspend fun getByBreed(breed: String): DogPhotosResponseDto
    suspend fun getByBreedAndSubBreed(breed: String, subBreed: String): DogPhotosResponseDto
    suspend fun getDogPhotosFromDb(breed: String, subBreed: String): List<DogPhotosEntity>
    suspend fun storeItIntoDb(dogPhotosEntityList: List<DogPhotosEntity>)
}