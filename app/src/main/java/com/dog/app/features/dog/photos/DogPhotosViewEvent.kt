package com.dog.app.features.dog.photos

sealed class DogPhotosViewEvent {
    object Refresh : DogPhotosViewEvent()
    object TryAgain : DogPhotosViewEvent()
}