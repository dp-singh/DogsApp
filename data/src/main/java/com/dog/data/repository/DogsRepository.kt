package com.dog.data.repository

import com.dog.data.model.local.DogEntity
import com.dog.data.model.remote.DogsResponseDto

interface DogsRepository {
    suspend fun getDogListFromNetwork(): DogsResponseDto
    suspend fun getDogTypeFromDb(): List<DogEntity>
    suspend fun persistIntoDb(dogsTypeEntity: List<DogEntity>)
}