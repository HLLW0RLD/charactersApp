package com.example.charactersapp.data.remote.dto

data class CharactersPagingData(
    val count: Long,
    val next: String?,
    val previous: String?,
    val results: List<CharacterDTO>,
)