package com.dog.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dog(val breed: String, val subBreed: String? = null) : Parcelable