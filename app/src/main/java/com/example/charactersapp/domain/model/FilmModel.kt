package com.example.charactersapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class FilmModel(
    val title: String,
    val episodeId: Long,
    val openingCrawl: String,
    val director: String,
    val producer: String,
    val releaseDate: String,
    val created: String,
    val edited: String,
)
