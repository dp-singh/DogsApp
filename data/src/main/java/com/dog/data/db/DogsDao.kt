package com.dog.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dog.data.model.local.DogEntity

@Dao
interface DogsDao {
    @Query("SELECT * FROM Dogs")
    suspend fun getAll(): List<DogEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(dogsEntityList: List<DogEntity>)

    @Query("DELETE FROM Dogs")
    suspend fun deleteAll()
}