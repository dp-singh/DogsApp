package com.dog.app.features.dog.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dog.domain.model.Dog
import com.dog.domain.model.Resource
import com.dog.domain.usecase.GetDogPhotosUseCase
import com.dog.app.util.ViewEventListener
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DogPhotosViewModel(
    private val getBreedPhotos: GetDogPhotosUseCase,
    private val dog: Dog
) : ViewModel(), ViewEventListener<DogPhotosViewEvent> {
    private val _viewState = MutableLiveData<Resource<List<String>>>()
    val viewState: LiveData<Resource<List<String>>> = _viewState

    init {
        fetchDogPhotos()
    }

    private fun fetchDogPhotos() {
        _viewState.value = Resource.Loading
        viewModelScope.launch {
            _viewState.value = getBreedPhotos(dog)
        }
    }

    override fun onViewEvent(viewEvents: DogPhotosViewEvent) {
        when (viewEvents) {
            DogPhotosViewEvent.Refresh -> fetchDogPhotos()
            DogPhotosViewEvent.TryAgain -> fetchDogPhotos()
        }
    }
}