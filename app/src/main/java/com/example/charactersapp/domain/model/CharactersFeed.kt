package com.example.charactersapp.domain.model

data class CharactersFeed(
    val count: Long,
    val next: String,
    val previous: Any?,
    val results: List<Character>,
)