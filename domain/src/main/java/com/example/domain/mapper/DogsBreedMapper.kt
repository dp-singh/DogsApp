package com.example.domain.mapper

import com.example.data.model.local.DogTypeEntity
import com.example.data.model.remote.DogsBreedResponseDto
import com.example.domain.model.DogType

class DogsBreedMapper : BaseMapper<DogsBreedResponseDto, List<DogType>> {
    override fun toEntity(dto: DogsBreedResponseDto): List<DogType> {
        return dto.mapOfBreedAndSubBreed.map { (breed, subBreedList) ->
            toListDogType(breed, subBreedList)
        }.flatten()
    }

    private fun toListDogType(breed: String, subBreedList: List<String>): List<DogType> {
        return if (subBreedList.isNullOrEmpty()) {
            listOf(DogType(breed = breed))
        } else {
            subBreedList.map { subBreed -> DogType(breed = breed, subBreed = subBreed) }
        }
    }

    override fun toDto(entity: List<DogType>): DogsBreedResponseDto {
        val item: Map<String, List<String>> =
            entity.groupBy({ it.breed }, { it.subBreed.orEmpty() })
        return DogsBreedResponseDto(mapOfBreedAndSubBreed = item)
    }
}

class DogsBreedEntityToDbMapper : BaseMapper<List<DogTypeEntity>, List<DogType>> {
    override fun toEntity(dto: List<DogTypeEntity>): List<DogType> {
        return dto.map {
            DogType(
                breed = it.breed,
                subBreed = it.subBreed.ifEmpty { null }
            )
        }
    }

    override fun toDto(entity: List<DogType>): List<DogTypeEntity> {
        return entity.map {
            DogTypeEntity(
                breed = it.breed,
                subBreed = it.subBreed.orEmpty()
            )
        }
    }

}