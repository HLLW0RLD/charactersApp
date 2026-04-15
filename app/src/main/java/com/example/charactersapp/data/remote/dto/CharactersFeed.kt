package com.example.charactersapp.data.remote.dto

data class CharactersFeed(
    val count: Long,
    val next: String,
    val previous: Any?,
    val results: List<Character>,
)