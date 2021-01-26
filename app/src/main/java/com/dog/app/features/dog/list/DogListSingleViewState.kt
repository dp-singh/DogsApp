package com.dog.app.features.dog.list

import com.dog.domain.model.Dog

sealed class DogListSingleViewState {
    data class NavToDogPhotos(val dog: Dog) : DogListSingleViewState()
}