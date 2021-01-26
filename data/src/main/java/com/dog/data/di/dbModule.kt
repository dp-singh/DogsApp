package com.dog.data.di

import androidx.room.Room
import com.dog.data.db.AppDatabase
import com.dog.data.db.AppDatabase.Companion.DB_NAME
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            DB_NAME
        ).build()
    }
}