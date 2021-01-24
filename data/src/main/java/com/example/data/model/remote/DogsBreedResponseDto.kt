package com.example.data.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DogsBreedResponseDto(
    @Json(name = "message")
    val mapOfBreedAndSubBreed: Map<String, List<String>>
)
