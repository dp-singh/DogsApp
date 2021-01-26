package com.dog.domain.mapper.dog

import com.dog.data.model.local.DogEntity
import com.dog.domain.mapper.Mapper
import com.dog.domain.model.Dog

class DogsFromDataToDbMapper : Mapper<List<Dog>, List<DogEntity>> {
    override fun map(input: List<Dog>): List<DogEntity> {
        return input.map { dog ->
            DogEntity(
                breed = dog.breed,
                subBreed = dog.subBreed.orEmpty()
            )
        }
    }
}