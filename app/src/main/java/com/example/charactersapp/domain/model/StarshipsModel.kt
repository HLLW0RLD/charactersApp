package com.example.charactersapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class StarshipsModel(
    val name: String,
    val model: String,
    val manufacturer: String,
    val costInCredits: String,
    val length: String,
    val maxAtmospheringSpeed: String,
    val crew: String,
    val passengers: String,
    val cargoCapacity: String,
    val consumables: String,
    val hyperdriveRating: String,
    val mglt: String,
    val starshipClass: String,
    val created: String,
    val edited: String,
    val url: String,
)
