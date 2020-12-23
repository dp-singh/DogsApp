package com.example.data.di

import com.example.data.api.EmployeeApi
import com.example.data.respository.EmployeeRepository
import com.example.data.respository.EmployeeRepositoryImpl
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

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
            .baseUrl("http://dummy.restapiexample.com")
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    factory<EmployeeApi> {
        get<Retrofit>().create(EmployeeApi::class.java)
    }

    factory<EmployeeRepository> {
        EmployeeRepositoryImpl(
            employeeApi = get()
        )
    }
}

