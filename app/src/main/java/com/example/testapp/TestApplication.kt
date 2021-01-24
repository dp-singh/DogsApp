package com.example.testapp

import android.app.Application
import com.example.data.di.dbModule
import com.example.data.di.httpModule
import com.example.domain.di.useCaseModule
import com.example.testapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin

class TestApplication : Application() {
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
            modules(httpModule + viewModelModule + useCaseModule + dbModule)
        }
    }
}