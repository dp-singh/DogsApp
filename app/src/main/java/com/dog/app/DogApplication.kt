package com.dog.app

import android.app.Application
import com.dog.app.di.applicationModule
import com.dog.data.di.dbModule
import com.dog.data.di.httpModule
import com.dog.domain.di.useCaseModule
import com.dog.app.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin

class DogApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            if (BuildConfig.DEBUG) {
                logger(AndroidLogger())
            }
            androidContext(applicationContext)
            modules(listOf(httpModule, viewModelModule, useCaseModule, dbModule, applicationModule))
        }
    }
}