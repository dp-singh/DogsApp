package com.example.testapp.di

import androidx.lifecycle.Lifecycle
import com.example.testapp.screenhome.HomeViewPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module


@ExperimentalCoroutinesApi
val viewModelModule = module {
    factory { (lifecycle: Lifecycle) ->
        HomeViewPresenter(getTopPaidEmployeeUseCase = get(), lifecycle = lifecycle)
    }
}