package com.example.testapp.features.breedlist

import com.example.domain.model.DogType

sealed class HomeSingleViewState {
    data class NavToDogPhotosFragment(val dogType: DogType) : HomeSingleViewState()
}