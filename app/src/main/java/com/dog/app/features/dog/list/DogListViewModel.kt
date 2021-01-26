package com.dog.app.features.dog.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dog.domain.model.Dog
import com.dog.domain.model.Resource
import com.dog.domain.usecase.GetDogListUseCase
import com.dog.app.util.SingleLiveData
import com.dog.app.util.ViewEventListener
import kotlinx.coroutines.launch

class DogListViewModel(
    private val getDogList: GetDogListUseCase
) : ViewModel(), ViewEventListener<DogListViewEvents> {

    private val _viewState = MutableLiveData<Resource<List<Dog>>>()
    val viewState: LiveData<Resource<List<Dog>>>
        get() = _viewState

    private val _singleViewState = SingleLiveData<DogListSingleViewState>()
    val singleViewState: LiveData<DogListSingleViewState>
        get() = _singleViewState

    init {
        fetchDogList()
    }

    private fun fetchDogList() {
        _viewState.value = Resource.Loading
        viewModelScope.launch {
            _viewState.value = getDogList()
        }
    }

    override fun onViewEvent(viewEvents: DogListViewEvents) {
        when (viewEvents) {
            DogListViewEvents.Refresh -> fetchDogList()
            DogListViewEvents.TryAgain -> fetchDogList()
            is DogListViewEvents.OpenDogPhotosScreen -> _singleViewState.setValue(
                DogListSingleViewState.NavToDogPhotos(
                    dog = viewEvents.dog
                )
            )
        }
    }
}