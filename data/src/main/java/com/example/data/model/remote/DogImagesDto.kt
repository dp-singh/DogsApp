package com.example.data.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DogImagesDto(
    @Json(name = "message")
    val photoList: List<String>
)