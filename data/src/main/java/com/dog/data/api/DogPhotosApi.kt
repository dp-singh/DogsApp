package com.dog.data.api

import com.dog.data.model.remote.DogPhotosResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DogPhotosApi {
    @GET("/api/breed/{breed}/images")
    suspend fun getImagesByBreed(@Path("breed") breed: String): DogPhotosResponseDto

    @GET("/api/breed/{breed}/{sub_breed}/images")
    suspend fun getImagesBySubBreed(
        @Path("breed") breed: String,
        @Path("sub_breed") subBreed: String
    ): DogPhotosResponseDto
}