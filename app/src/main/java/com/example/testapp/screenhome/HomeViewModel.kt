package com.example.testapp.screenhome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Resource
import com.example.domain.model.TopEmployeeWithSalary200
import com.example.domain.usecase.GetTopPaidEmployeeUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getTopPaidEmployeeUseCase: GetTopPaidEmployeeUseCase
) : ViewModel() {
    private val _viewState = MutableLiveData<Resource<TopEmployeeWithSalary200>>()
    val viewState: LiveData<Resource<TopEmployeeWithSalary200>> = _viewState

    init {
        fetchTopPaidEmployee()
    }

    private fun fetchTopPaidEmployee() {
        _viewState.value = Resource.Loading
        viewModelScope.launch {
            _viewState.value = getTopPaidEmployeeUseCase()
        }
    }

    fun onViewEvent(eventsHome: HomeViewEvents) {
        when (eventsHome) {
            HomeViewEvents.Refresh -> fetchTopPaidEmployee()
        }
    }
}