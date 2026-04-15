package com.example.charactersapp.presentation.di

import com.example.charactersapp.presentation.screen.viewModel.CharactersFeedViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { CharactersFeedViewModel() }

}