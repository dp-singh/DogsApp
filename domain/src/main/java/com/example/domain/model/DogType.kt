package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DogType(val breed: String, val subBreed: String? = null) : Parcelable