package com.dog.domain.di

import com.dog.domain.mapper.dog.DogsFromDataToDbMapper
import com.dog.domain.mapper.dog.DogsFromDbToDataMapper
import com.dog.domain.mapper.dog.DogsFromNetworkToDataMapper
import com.dog.domain.usecase.GetDogListUseCase
import com.dog.domain.utils.AppDispatchers
import com.dog.domain.usecase.GetDogPhotosUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        DogsFromNetworkToDataMapper()
    }

    factory {
        DogsFromDbToDataMapper()
    }

    factory {
        DogsFromDataToDbMapper()
    }

    factory {
        GetDogListUseCase(
            networkToDataMapper = get(),
            dogsRepository = get(),
            dbToDataMapper = get(),
            dataToDbMapper = get(),
            dispatcher = get()
        )
    }

    factory {
        GetDogPhotosUseCase(
            dogPhotosRepository = get(),
            dispatcher = get()
        )
    }

    single {
        AppDispatchers()
    }
}

