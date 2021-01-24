package com.example.data.api

import com.example.data.model.remote.DogImagesDto
import com.example.data.model.remote.DogsBreedResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsApi {
    @GET("/api/breeds/list/all")
    suspend fun getListOfAllBreeds(): DogsBreedResponseDto

    @GET("/api/breed/{breed}/images")
    suspend fun getImagesByBreed(@Path("breed") breed: String): DogImagesDto

    @GET("/api/breed/{breed}/{sub_breed}/images")
    suspend fun getImagesBySubBreed(
        @Path("breed") breed: String,
        @Path("sub_breed") subBreed: String
    ): DogImagesDto
}
