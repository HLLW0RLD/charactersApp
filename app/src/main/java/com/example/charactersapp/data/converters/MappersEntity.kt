package com.example.charactersapp.data.converters

import com.example.charactersapp.data.local.entity.CharacterEntity
import com.example.charactersapp.domain.model.CharacterFeedModel
import com.example.charactersapp.domain.model.CharacterModel
import kotlinx.serialization.json.Json

fun CharacterModel.toEntity(id: String): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        height = height,
        mass = mass,
        hairColor = hairColor,
        skinColor = skinColor,
        eyeColor = eyeColor,
        birthYear = birthYear,
        gender = gender,
        homeworldJson = homeworld?.let { Json.encodeToString(it) } ?: "",
        filmsJson = Json.encodeToString(films),
        speciesJson = Json.encodeToString(species),
        vehiclesJson = Json.encodeToString(vehicles),
        starshipsJson = Json.encodeToString(starships),
        created = created,
        edited = edited
    )
}

fun CharacterEntity.toModel(): CharacterModel {
    return CharacterModel(
        name = name,
        height = height,
        mass = mass,
        hairColor = hairColor,
        skinColor = skinColor,
        eyeColor = eyeColor,
        birthYear = birthYear,
        gender = gender,
        homeworld = if (homeworldJson.isNotEmpty()) {
            Json.decodeFromString(homeworldJson)
        } else null,
        created = created,
        edited = edited,
        films = Json.decodeFromString(filmsJson),
        species = Json.decodeFromString(speciesJson),
        vehicles = Json.decodeFromString(vehiclesJson),
        starships = Json.decodeFromString(starshipsJson)
    )
}

fun CharacterEntity.toFeedModel(): CharacterFeedModel {
    return CharacterFeedModel(
        id = id,
        name = name,
        height = height,
        mass = mass,
        hairColor = hairColor,
        skinColor = skinColor,
        eyeColor = eyeColor,
        birthYear = birthYear,
        gender = gender
    )
}