package com.example.charactersapp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.charactersapp.data.converters.Converters
import com.example.charactersapp.data.local.entity.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}