package com.example.charactersapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterFeedModel(
    val id: String,
    val name: String,
    val height: String,
    val mass: String,
    val hairColor: String,
    val skinColor: String,
    val eyeColor: String,
    val birthYear: String,
    val gender: String,
)