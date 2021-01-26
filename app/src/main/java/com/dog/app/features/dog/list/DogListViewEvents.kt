package com.dog.app.features.dog.list

import com.dog.domain.model.Dog

sealed class DogListViewEvents {
    object Refresh : DogListViewEvents()
    object TryAgain : DogListViewEvents()
    data class OpenDogPhotosScreen(val dog: Dog) : DogListViewEvents()
}