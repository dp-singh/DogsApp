package com.example.data.di

import com.example.data.api.DogsApi
import com.example.data.db.AppDatabase
import com.example.data.repository.DogsBreedRepository
import com.example.data.repository.DogsBreedRepositoryImpl
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
        httpClient.addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
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

    factory<DogsBreedRepository> {
        DogsBreedRepositoryImpl(dogsApi = get(), dogTypeDao = get<AppDatabase>().dogTypeDao())
    }
}

