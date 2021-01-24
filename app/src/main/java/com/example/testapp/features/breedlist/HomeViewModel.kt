package com.example.testapp.features.breedlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.DogType
import com.example.domain.model.Resource
import com.example.domain.usecase.GetDogBreedsUseCase
import com.example.testapp.util.SingleLiveData
import com.example.testapp.util.ViewEventListener
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getDogBreeds: GetDogBreedsUseCase
) : ViewModel(), ViewEventListener<HomeViewEvents> {

    private val _viewState = MutableLiveData<Resource<List<DogType>>>()
    val viewState: LiveData<Resource<List<DogType>>>
        get() = _viewState

    private val _singleViewState = SingleLiveData<HomeSingleViewState>()
    val singleViewState: LiveData<HomeSingleViewState>
        get() = _singleViewState

    init {
        fetchTopPaidEmployee()
    }

    private fun fetchTopPaidEmployee() {
        _viewState.value = Resource.Loading
        viewModelScope.launch {
            delay(2000)
            _viewState.value = getDogBreeds()
        }
    }

    override fun onViewEvent(viewEvents: HomeViewEvents) {
        when (viewEvents) {
            HomeViewEvents.Refresh -> fetchTopPaidEmployee()
            is HomeViewEvents.OpenDogPhotosScreen -> _singleViewState.setValue(
                HomeSingleViewState.NavToDogPhotosFragment(
                    dogType = viewEvents.dogType
                )
            )
        }
    }
}