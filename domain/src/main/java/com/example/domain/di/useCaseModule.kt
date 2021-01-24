package com.example.domain.di

import com.example.domain.mapper.DogsBreedEntityToDbMapper
import com.example.domain.mapper.DogsBreedMapper
import com.example.domain.usecase.GetDogBreedsUseCase
import com.example.domain.usecase.DispatchersUseCase
import com.example.domain.usecase.GetDogBreedTypePhotosUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        DogsBreedMapper()
    }

    factory {
        DogsBreedEntityToDbMapper()
    }
    factory {
        GetDogBreedsUseCase(
            dogsBreedMapper = get(),
            dogsBreedRepository = get(),
            dogsBreedEntityToDbMapper = get(),
            dispatcher = get()
        )
    }

    factory {
        GetDogBreedTypePhotosUseCase(
            dogsBreedRepository = get(),
            dispatcher = get()
        )
    }

    single {
        DispatchersUseCase()
    }
}

