package com.example.moviesapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [MoviesPageResponse::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun moviePageDao(): MovieResponseDao


    companion object {

        private var instance: AppDatabase? = null

        fun getInstance(appContext: Context): AppDatabase {

            return instance ?: synchronized(this) {
                instance ?: buildDatabase(appContext).also { instance = it }
            }
        }

        private fun buildDatabase(appContext: Context): AppDatabase {
            return Room
                .databaseBuilder(appContext, AppDatabase::class.java, "myDatabase")
                .build()
        }

    }

}