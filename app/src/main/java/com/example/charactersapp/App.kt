package com.example.charactersapp

import android.app.Application
import com.example.charactersapp.data.di.apiModule
import com.example.charactersapp.data.di.dbModule
import com.example.charactersapp.data.di.repoModule
import com.example.charactersapp.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        instance  = this

        startKoin {
            androidContext(this@App)
            modules(
                apiModule,
                dbModule,
                repoModule,
                viewModelModule
            )
        }
    }

    companion object {
        lateinit var instance: App
            private set
    }
}