package com.example.charactersapp.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.charactersapp.data.local.entity.CharacterEntity
import com.example.charactersapp.domain.model.CharacterFeedModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharacterEntity)

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharacterById(id: String): CharacterEntity?

    @Query("SELECT * FROM characters ORDER BY name ASC")
    suspend fun getAllCharacters(): List<CharacterEntity>

    @Query("DELETE FROM characters")
    suspend fun clearAllCharacters()
}