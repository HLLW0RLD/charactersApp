package com.example.charactersapp.domain.repository

import com.example.charactersapp.domain.model.CharacterFeedModel
import com.example.charactersapp.domain.model.CharacterModel

interface ILocalRepository {
    suspend fun saveCharacter(character: CharacterModel, id: String)
    suspend fun getCharacter(id: String): CharacterModel?
    suspend fun getAllCharactersFeed(): List<CharacterFeedModel>
    suspend fun clearCache()
}