package com.example.charactersapp.data.converters

import androidx.room.TypeConverter
import com.example.charactersapp.domain.model.FilmModel
import com.example.charactersapp.domain.model.PlanetModel
import com.example.charactersapp.domain.model.SpeciesModel
import com.example.charactersapp.domain.model.StarshipsModel
import com.example.charactersapp.domain.model.VehiclesModel
import kotlinx.serialization.json.Json

class Converters {

    @TypeConverter
    fun fromPlanetModel(planet: PlanetModel?): String {
        return if (planet != null) {
            Json.Default.encodeToString(planet)
        } else ""
    }

    @TypeConverter
    fun toPlanetModel(json: String): PlanetModel? {
        return if (json.isNotEmpty()) {
            Json.Default.decodeFromString(json)
        } else null
    }

    @TypeConverter
    fun fromFilmList(films: List<FilmModel>): String {
        return Json.Default.encodeToString(films)
    }

    @TypeConverter
    fun toFilmList(json: String): List<FilmModel> {
        return if (json.isNotEmpty()) {
            Json.Default.decodeFromString(json)
        } else emptyList()
    }

    @TypeConverter
    fun fromSpeciesList(species: List<SpeciesModel>): String {
        return Json.Default.encodeToString(species)
    }

    @TypeConverter
    fun toSpeciesList(json: String): List<SpeciesModel> {
        return if (json.isNotEmpty()) {
            Json.Default.decodeFromString(json)
        } else emptyList()
    }

    @TypeConverter
    fun fromVehiclesList(vehicles: List<VehiclesModel>): String {
        return Json.Default.encodeToString(vehicles)
    }

    @TypeConverter
    fun toVehiclesList(json: String): List<VehiclesModel> {
        return if (json.isNotEmpty()) {
            Json.Default.decodeFromString(json)
        } else emptyList()
    }

    @TypeConverter
    fun fromStarshipsList(starships: List<StarshipsModel>): String {
        return Json.Default.encodeToString(starships)
    }

    @TypeConverter
    fun toStarshipsList(json: String): List<StarshipsModel> {
        return if (json.isNotEmpty()) {
            Json.Default.decodeFromString(json)
        } else emptyList()
    }
}