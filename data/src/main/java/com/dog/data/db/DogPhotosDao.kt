package com.dog.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dog.data.model.local.DogPhotosEntity

@Dao
interface DogPhotosDao {
    @Query("SELECT * FROM DogPhotos where breed=:breed AND sub_breed=:subBreed")
    suspend fun getByBreedAndSubBreed(breed: String, subBreed: String): List<DogPhotosEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(dogsEntityList: List<DogPhotosEntity>)

    @Query("DELETE FROM DogPhotos")
    suspend fun deleteAll()
}