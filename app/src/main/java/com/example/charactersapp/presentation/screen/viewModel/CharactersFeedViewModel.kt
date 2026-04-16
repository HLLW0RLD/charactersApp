package com.example.charactersapp.presentation.screen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.charactersapp.domain.useCase.GetCharactersFeedUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharactersFeedViewModel(
    private val getCharactersFeedUseCase: GetCharactersFeedUseCase
): ViewModel() {


    val characters = getCharactersFeedUseCase
        .getCharactersFeed()
        .cachedIn(viewModelScope)

}