package com.example.charactersapp.domain.useCase

import com.example.charactersapp.domain.model.CharacterModel
import com.example.charactersapp.domain.repository.ILocalRepository
import com.example.charactersapp.domain.repository.IRemoteRepository
import com.example.charactersapp.presentation.utils.logError

class GetCharactersDetailsUseCase(
    val remoteRepository: IRemoteRepository,
    val localRepository: ILocalRepository
) {

    suspend fun getCharacter(characterId: String): CharacterModel? {

        localRepository.getCharacter(characterId)?.let {
            return it
        }

        return try {
            val character = remoteRepository.getCharacter(characterId)

            character?.let {
                localRepository.saveCharacter(character, characterId)
            }

            character
        } catch (e: Exception) {
            logError(e)
            null
        }
    }
}