package com.example.data.model.local

import androidx.room.*

@Entity(tableName = "DogType", primaryKeys = ["breed", "sub_breed"])
data class DogTypeEntity(
    @ColumnInfo(name = "breed") val breed: String,
    @ColumnInfo(name = "sub_breed") val subBreed: String
)