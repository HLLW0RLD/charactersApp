package com.example.charactersapp.data.local.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class AppDataBase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "local_characters.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}