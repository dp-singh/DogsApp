package com.dog.domain.usecase

import com.dog.data.model.local.DogPhotosEntity
import com.dog.data.repository.DogPhotosRepository
import com.dog.domain.model.Dog
import com.dog.domain.model.Resource
import com.dog.domain.utils.AppDispatchers
import kotlinx.coroutines.withContext

class GetDogPhotosUseCase(
    private val dogPhotosRepository: DogPhotosRepository,
    private val dispatcher: AppDispatchers
) {
    private suspend fun getFromPersistence(
        parentThrowable: Throwable,
        dog: Dog
    ): Resource<List<String>> {
        return withContext(dispatcher.ioDispatcher) {
            try {
                val result = dogPhotosRepository.getDogPhotosFromDb(
                    breed = dog.breed,
                    subBreed = dog.subBreed.orEmpty()
                )
                Resource.SuccessFromCache(result.map { it.imageUrl }, parentThrowable)
            } catch (exception: Exception) {
                Resource.Error(parentThrowable)
            }
        }
    }

    private suspend fun getFromRemoteAndPersist(dog: Dog): Resource<List<String>> {
        return try {
            val result = if (dog.subBreed != null) {
                dogPhotosRepository.getByBreedAndSubBreed(dog.breed, dog.subBreed)
            } else {
                dogPhotosRepository.getByBreed(dog.breed)
            }
            dogPhotosRepository.storeItIntoDb(mapToDogPhotosEntity(dog, result.photoList))
            Resource.Success(result.photoList)
        } catch (exception: Exception) {
            Resource.Error(exception)
        }
    }

    private fun mapToDogPhotosEntity(dog: Dog, photos: List<String>): List<DogPhotosEntity> {
        return photos.map { imageUrl ->
            DogPhotosEntity(
                breed = dog.breed,
                subBreed = dog.subBreed.orEmpty(),
                imageUrl = imageUrl
            )
        }
    }

    suspend operator fun invoke(dog: Dog): Resource<List<String>> {
        val result = getFromRemoteAndPersist(dog)
        return if (result is Resource.Error) {
            getFromPersistence(result.throwable, dog)
        } else {
            result
        }
    }
}