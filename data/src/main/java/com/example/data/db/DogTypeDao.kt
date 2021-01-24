package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.local.DogTypeEntity

@Dao
interface DogTypeDao {
    @Query("SELECT * FROM DogType")
    suspend fun getAll(): List<DogTypeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(dogTypeList: List<DogTypeEntity>)

    @Query("DELETE FROM DogType")
    suspend fun deleteAll()
}