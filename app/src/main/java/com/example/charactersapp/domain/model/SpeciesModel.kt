package com.example.charactersapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SpeciesModel(
    val name: String,
    val classification: String,
    val designation: String,
    val averageHeight: String,
    val skinColors: String,
    val hairColors: String,
    val eyeColors: String,
    val averageLifespan: String,
    val homeworld: String,
    val language: String,
    val created: String,
    val edited: String,
)