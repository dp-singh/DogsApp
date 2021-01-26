package com.dog.domain.mapper.dog

import com.dog.data.model.local.DogEntity
import com.dog.domain.mapper.Mapper
import com.dog.domain.model.Dog

class DogsFromDbToDataMapper : Mapper<List<DogEntity>, List<Dog>> {
    override fun map(input: List<DogEntity>): List<Dog> {
        return input.map { dogEntity ->
            Dog(
                breed = dogEntity.breed,
                subBreed = dogEntity.subBreed.ifEmpty { null }
            )
        }
    }
}


