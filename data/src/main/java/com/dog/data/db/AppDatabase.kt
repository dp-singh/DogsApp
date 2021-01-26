package com.dog.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dog.data.model.local.DogEntity
import com.dog.data.model.local.DogPhotosEntity


@Database(entities = [DogEntity::class, DogPhotosEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dogsDao(): DogsDao
    abstract fun dogPhotosDao(): DogPhotosDao

    companion object {
        const val DB_NAME = "dog_db"
    }
}