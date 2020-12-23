package com.example.testapp.di

import com.example.testapp.screenhome.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        HomeViewModel(getTopPaidEmployeeUseCase = get())
    }
}