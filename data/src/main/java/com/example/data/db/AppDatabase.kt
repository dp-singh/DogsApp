package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.model.local.DogTypeEntity


@Database(entities = [DogTypeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dogTypeDao(): DogTypeDao

    companion object {
        const val DB_NAME = "dog_db"
    }
}