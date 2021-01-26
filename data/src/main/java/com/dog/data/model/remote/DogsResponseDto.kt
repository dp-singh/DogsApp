package com.dog.data.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DogsResponseDto(
    @Json(name = "message")
    val mapOfBreedAndSubBreed: Map<String, List<String>>
)
