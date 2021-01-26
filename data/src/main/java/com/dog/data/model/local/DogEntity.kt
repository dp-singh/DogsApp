package com.dog.data.model.local

import androidx.room.*

@Entity(tableName = "Dogs", primaryKeys = ["breed", "sub_breed"])
data class DogEntity(
    @ColumnInfo(name = "breed") val breed: String,
    @ColumnInfo(name = "sub_breed") val subBreed: String
)
