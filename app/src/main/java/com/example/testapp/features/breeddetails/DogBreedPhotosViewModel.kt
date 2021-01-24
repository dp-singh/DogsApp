package com.example.testapp.features.breeddetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.DogType
import com.example.domain.model.Resource
import com.example.domain.usecase.GetDogBreedTypePhotosUseCase
import kotlinx.coroutines.launch

class DogBreedPhotosViewModel(
    private val getBreedPhotos: GetDogBreedTypePhotosUseCase,
    private val dogType: DogType
) :
    ViewModel() {
    private val _viewState = MutableLiveData<Resource<List<String>>>()
    val viewState: LiveData<Resource<List<String>>> = _viewState

    init {
        fetchTopPaidEmployee()
    }

    private fun fetchTopPaidEmployee() {
        _viewState.value = Resource.Loading
        viewModelScope.launch {
            _viewState.value = getBreedPhotos(dogType)
        }
    }
}