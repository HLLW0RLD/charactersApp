package com.example.charactersapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Film(
    val title: String,
    @SerializedName("episode_id")
    val episodeId: Long,
    @SerializedName("opening_crawl")
    val openingCrawl: String,
    val director: String,
    val producer: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val created: String,
    val edited: String,
    val url: String,

    val characters: List<String>,
    val planets: List<String>,
    val starships: List<String>,
    val vehicles: List<String>,
    val species: List<String>,
)