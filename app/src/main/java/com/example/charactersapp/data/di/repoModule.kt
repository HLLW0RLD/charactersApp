package com.example.charactersapp.data.di

import com.example.charactersapp.data.local.repository.LocalRepository
import com.example.charactersapp.data.remote.repository.RemoteRepository
import com.example.charactersapp.domain.repository.ILocalRepository
import com.example.charactersapp.domain.repository.IRemoteRepository
import org.koin.dsl.module

val repoModule = module {
    single<IRemoteRepository> { RemoteRepository(get()) }
    single<ILocalRepository> { LocalRepository(get()) }
}