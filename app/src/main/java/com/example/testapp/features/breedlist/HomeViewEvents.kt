package com.example.testapp.features.breedlist

import com.example.domain.model.DogType

sealed class HomeViewEvents {
    object Refresh : HomeViewEvents()
    data class OpenDogPhotosScreen(val dogType: DogType) : HomeViewEvents()
}