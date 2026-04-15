package com.example.charactersapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Vehicles(
    val name: String,
    val model: String,
    val manufacturer: String,
    @SerializedName("cost_in_credits")
    val costInCredits: String,
    val length: String,
    @SerializedName("max_atmosphering_speed")
    val maxAtmospheringSpeed: String,
    val crew: String,
    val passengers: String,
    @SerializedName("cargo_capacity")
    val cargoCapacity: String,
    val consumables: String,
    @SerializedName("vehicle_class")
    val vehicleClass: String,
    val created: String,
    val edited: String,
    val url: String,

    val pilots: List<String>,
    val films: List<String>,
)