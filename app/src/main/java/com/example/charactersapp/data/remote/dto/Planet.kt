package com.example.charactersapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Planet(
    val name: String,
    @SerializedName("rotation_period")
    val rotationPeriod: String,
    @SerializedName("orbital_period")
    val orbitalPeriod: String,
    val diameter: String,
    val climate: String,
    val gravity: String,
    val terrain: String,
    @SerializedName("surface_water")
    val surfaceWater: String,
    val population: String,
    val created: String,
    val edited: String,
    val url: String,

    val residents: List<String>,
    val films: List<String>,
)