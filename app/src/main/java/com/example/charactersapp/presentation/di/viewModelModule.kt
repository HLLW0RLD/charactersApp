package com.example.charactersapp.presentation.di

import com.example.charactersapp.domain.useCase.GetCharactersDetailsUseCase
import com.example.charactersapp.domain.useCase.GetCharactersFeedUseCase
import com.example.charactersapp.presentation.screen.viewModel.CharactersFeedViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    factory { GetCharactersDetailsUseCase(get(), get()) }
    factory { GetCharactersFeedUseCase(get(), get()) }

    viewModel { CharactersFeedViewModel(get()) }

}