package com.dog.app.di

import com.dog.app.util.DefaultErrorHandler
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val applicationModule = module {
    single {
        DefaultErrorHandler(resourceManager = androidContext().resources)
    }
}