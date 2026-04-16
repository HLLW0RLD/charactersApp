package com.example.charactersapp.presentation.screen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charactersapp.domain.model.CharacterModel
import com.example.charactersapp.domain.useCase.GetCharactersDetailsUseCase
import com.example.charactersapp.presentation.utils.logError
import com.example.charactersapp.presentation.utils.toast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

sealed class CharacterDetailsState {
    object Loading : CharacterDetailsState()
    data class Success(val character: CharacterModel?) : CharacterDetailsState()
    object Error : CharacterDetailsState()
}

class CharacterDetailsViewModel(
    private val getCharacterDetailsUseCase: GetCharactersDetailsUseCase
): ViewModel() {

    private val _characterDetailsState = MutableStateFlow<CharacterDetailsState?>(null)
    val characterDetailsState = _characterDetailsState

    fun loadCharacter(characterId: String) {
        viewModelScope.launch {
            _characterDetailsState.value = CharacterDetailsState.Loading
            try {
                val result = getCharacterDetailsUseCase.getCharacter(characterId)
                _characterDetailsState.value = CharacterDetailsState.Success(result)
            } catch (e: Exception) {
                _characterDetailsState.value = CharacterDetailsState.Error
                logError(e)
                toast(e.message ?: "")
            }
        }
    }
}