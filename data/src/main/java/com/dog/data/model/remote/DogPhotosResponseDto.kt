package com.dog.data.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DogPhotosResponseDto(
    @Json(name = "message")
    val photoList: List<String>
)