package com.example.charactersapp.data.remote.paging

import com.example.charactersapp.domain.model.CharacterFeedModel

data class CharactersPage(
    val characters: List<CharacterFeedModel>,
    val hasNextPage: Boolean,
    val hasPreviousPage: Boolean,
    val isSuccess: Boolean
)