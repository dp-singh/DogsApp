package com.dog.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DogPhotos")
data class DogPhotosEntity(
    @PrimaryKey @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "breed") val breed: String,
    @ColumnInfo(name = "sub_breed") val subBreed: String,
)