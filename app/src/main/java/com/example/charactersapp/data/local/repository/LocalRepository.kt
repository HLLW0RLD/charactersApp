package com.example.charactersapp.data.local.repository

import com.example.charactersapp.data.converters.toEntity
import com.example.charactersapp.data.converters.toFeedModel
import com.example.charactersapp.data.converters.toModel
import com.example.charactersapp.data.local.db.CharactersDao
import com.example.charactersapp.domain.model.CharacterFeedModel
import com.example.charactersapp.domain.model.CharacterModel
import com.example.charactersapp.domain.repository.ILocalRepository

class LocalRepository(val dao: CharactersDao): ILocalRepository {

    override suspend fun saveCharacter(character: CharacterModel, id: String) {
        dao.insertCharacter(character.toEntity(id))
    }

    override suspend fun getCharacter(id: String): CharacterModel? {
        return dao.getCharacterById(id)?.toModel()
    }

    override suspend fun getAllCharactersFeed(): List<CharacterFeedModel> {
        return dao.getAllCharacters().map { it.toFeedModel() }
    }

    override suspend fun clearCache() {
        dao.clearAllCharacters()
    }
}