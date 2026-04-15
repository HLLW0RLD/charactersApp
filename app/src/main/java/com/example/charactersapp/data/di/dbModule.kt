package com.example.charactersapp.data.di

import androidx.room.Room
import com.example.charactersapp.data.local.db.AppDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDataBase::class.java,
            "characters.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<AppDataBase>().charactersDao() }
}