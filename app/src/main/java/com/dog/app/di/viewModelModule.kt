package com.dog.app.di

import com.dog.domain.model.Dog
import com.dog.app.features.dog.photos.DogPhotosViewModel
import com.dog.app.features.dog.list.DogListViewModel
import com.dog.app.util.DefaultErrorHandler
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        DogListViewModel(getDogList = get())
    }

    viewModel { (dog: Dog) ->
        DogPhotosViewModel(getBreedPhotos = get(), dog = dog)
    }
}