package com.example.testapp.di

import com.example.domain.model.DogType
import com.example.testapp.features.breeddetails.DogBreedPhotosViewModel
import com.example.testapp.features.breedlist.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        HomeViewModel(getDogBreeds = get())
    }

    viewModel { (dogType: DogType) ->
        DogBreedPhotosViewModel(getBreedPhotos = get(), dogType = dogType)
    }
}