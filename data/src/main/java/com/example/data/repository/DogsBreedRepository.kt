package com.example.data.repository

import com.example.data.model.local.DogTypeEntity
import com.example.data.model.remote.DogImagesDto
import com.example.data.model.remote.DogsBreedResponseDto

interface DogsBreedRepository {
    suspend fun getDogTypeFromRemote(): DogsBreedResponseDto
    suspend fun getDogTypeFromDb(): List<DogTypeEntity>
    suspend fun persistIntoDb(dogsType: List<DogTypeEntity>)
    suspend fun getDogBreedTypePhotos(breed: String): DogImagesDto
    suspend fun getDogSubBreedTypePhotos(breed: String, subBreed: String): DogImagesDto
}