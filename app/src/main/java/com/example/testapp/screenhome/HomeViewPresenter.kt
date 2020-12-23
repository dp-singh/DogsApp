package com.example.testapp.screenhome

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.coroutineScope
import com.example.domain.model.Resource
import com.example.domain.model.TopEmployeeWithSalary200
import com.example.domain.usecase.GetTopPaidEmployeeUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class HomeViewPresenter(
    private val getTopPaidEmployeeUseCase: GetTopPaidEmployeeUseCase,
    private val lifecycle: Lifecycle
) : LifecycleObserver {

    init {
        lifecycle.addObserver(this)
    }

    private val _viewState = MutableStateFlow<Resource<TopEmployeeWithSalary200>>(Resource.Loading)
    val viewState: StateFlow<Resource<TopEmployeeWithSalary200>> = _viewState

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun start() {
        fetchTopPaidEmployee()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun destroy() {
        lifecycle.removeObserver(this)
    }

    fun onViewEvent(eventsHome: HomeViewEvents) {
        when (eventsHome) {
            HomeViewEvents.Refresh -> fetchTopPaidEmployee()
        }
    }

    private fun fetchTopPaidEmployee() {
        _viewState.value = Resource.Loading
        lifecycle.coroutineScope.launch {
            _viewState.value = getTopPaidEmployeeUseCase()
        }
    }
}