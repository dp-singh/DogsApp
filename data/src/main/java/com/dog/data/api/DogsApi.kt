package com.dog.data.api

import com.dog.data.model.remote.DogsResponseDto
import retrofit2.http.GET

interface DogsApi {
    @GET("/api/breeds/list/all")
    suspend fun getListOfAllBreeds(): DogsResponseDto
}
