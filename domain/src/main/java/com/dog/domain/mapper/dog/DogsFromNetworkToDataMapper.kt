package com.dog.domain.mapper.dog

import com.dog.data.model.remote.DogsResponseDto
import com.dog.domain.mapper.Mapper
import com.dog.domain.model.Dog

class DogsFromNetworkToDataMapper : Mapper<DogsResponseDto, List<Dog>> {
    override fun map(input: DogsResponseDto): List<Dog> {
        return input.mapOfBreedAndSubBreed.map { (breed, subBreedList) ->
            mapToDogList(breed, subBreedList)
        }.flatten()
    }

    private fun mapToDogList(breed: String, subBreedList: List<String>): List<Dog> {
        return if (subBreedList.isNullOrEmpty()) {
            listOf(Dog(breed = breed))
        } else {
            subBreedList.map { subBreed -> Dog(breed = breed, subBreed = subBreed) }
        }
    }
}