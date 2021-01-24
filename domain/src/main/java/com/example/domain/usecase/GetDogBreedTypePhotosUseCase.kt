package com.example.domain.usecase

import com.example.data.repository.DogsBreedRepository
import com.example.domain.model.DogType
import com.example.domain.model.Resource
import kotlinx.coroutines.withContext

class GetDogBreedTypePhotosUseCase(
    private val dogsBreedRepository: DogsBreedRepository,
    private val dispatcher: DispatchersUseCase
) {
    suspend operator fun invoke(dogType: DogType): Resource<List<String>> {
        return withContext(dispatcher.ioDispatcher) {
            try {
                Resource.Success(getDogPhotos(dogType))
            } catch (exception: Exception) {
                Resource.Error(exception)
            }
        }
    }

    private suspend fun getDogPhotos(dogType: DogType): List<String> {
        return if (dogType.subBreed != null) {
            dogsBreedRepository.getDogSubBreedTypePhotos(
                breed = dogType.breed,
                subBreed = dogType.subBreed
            ).photoList
        } else {
            dogsBreedRepository.getDogBreedTypePhotos(breed = dogType.breed).photoList
        }
    }
}