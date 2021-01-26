package com.dog.data.di

import com.dog.data.BuildConfig
import com.dog.data.api.DogPhotosApi
import com.dog.data.api.DogsApi
import com.dog.data.db.AppDatabase
import com.dog.data.repository.DogPhotosRepository
import com.dog.data.repository.DogPhotosRepositoryImpl
import com.dog.data.repository.DogsRepository
import com.dog.data.repository.DogsRepositoryImpl
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://dog.ceo"
val httpModule = module {

    single {
        val httpClient = OkHttpClient().newBuilder()
        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
        }
        httpClient.build()
    }

    single<Moshi> {
        Moshi.Builder().build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    factory<DogsApi> {
        get<Retrofit>().create(DogsApi::class.java)
    }

    factory<DogPhotosApi> {
        get<Retrofit>().create(DogPhotosApi::class.java)
    }

    factory<DogsRepository> {
        DogsRepositoryImpl(
            dogsApi = get(),
            dogsDao = get<AppDatabase>().dogsDao()
        )
    }

    factory<DogPhotosRepository> {
        DogPhotosRepositoryImpl(
            dogsPhotosApi = get(),
            dogPhotosDao = get<AppDatabase>().dogPhotosDao(),
        )
    }
}

