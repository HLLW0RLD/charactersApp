package com.example.charactersapp.data.converters

import com.example.charactersapp.data.remote.dto.CharacterDTO
import com.example.charactersapp.data.remote.dto.FilmDTO
import com.example.charactersapp.data.remote.dto.PlanetDTO
import com.example.charactersapp.data.remote.dto.SpeciesDTO
import com.example.charactersapp.data.remote.dto.StarshipsDTO
import com.example.charactersapp.data.remote.dto.VehiclesDTO
import com.example.charactersapp.domain.model.CharacterFeedModel
import com.example.charactersapp.domain.model.CharacterModel
import com.example.charactersapp.domain.model.FilmModel
import com.example.charactersapp.domain.model.PlanetModel
import com.example.charactersapp.domain.model.SpeciesModel
import com.example.charactersapp.domain.model.StarshipsModel
import com.example.charactersapp.domain.model.VehiclesModel

fun CharacterDTO.toFeedModel(id: String): CharacterFeedModel {
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

fun CharacterDTO.toBaseCharacterModel(
    homeworld: PlanetModel? = null,
    films: List<FilmModel> = emptyList(),
    species: List<SpeciesModel> = emptyList(),
    vehicles: List<VehiclesModel> = emptyList(),
    starships: List<StarshipsModel> = emptyList()
): CharacterModel {
    return CharacterModel(
        name = name,
        height = height,
        mass = mass,
        hairColor = hairColor,
        skinColor = skinColor,
        eyeColor = eyeColor,
        birthYear = birthYear,
        gender = gender,
        homeworld = homeworld,
        films = films,
        species = species,
        vehicles = vehicles,
        starships = starships,
        created = created,
        edited = edited
    )
}

fun PlanetDTO.toModel(): PlanetModel {
    return PlanetModel(
        name = name,
        rotationPeriod = rotationPeriod,
        orbitalPeriod = orbitalPeriod,
        diameter = diameter,
        climate = climate,
        gravity = gravity,
        terrain = terrain,
        surfaceWater = surfaceWater,
        population = population,
        created = created,
        edited = edited,
        url = url
    )
}

fun FilmDTO.toModel(): FilmModel {
    return FilmModel(
        title = title,
        episodeId = episodeId,
        openingCrawl = openingCrawl,
        director = director,
        producer = producer,
        releaseDate = releaseDate,
        created = created,
        edited = edited
    )
}

fun SpeciesDTO.toModel(): SpeciesModel {
    return SpeciesModel(
        name = name,
        classification = classification,
        designation = designation,
        averageHeight = averageHeight,
        skinColors = skinColors,
        hairColors = hairColors,
        eyeColors = eyeColors,
        averageLifespan = averageLifespan,
        homeworld = homeworld,
        language = language,
        created = created,
        edited = edited
    )
}

fun StarshipsDTO.toModel(): StarshipsModel {
    return StarshipsModel(
        name = name,
        model = model,
        manufacturer = manufacturer,
        costInCredits = costInCredits,
        length = length,
        maxAtmospheringSpeed = maxAtmospheringSpeed,
        crew = crew,
        passengers = passengers,
        cargoCapacity = cargoCapacity,
        consumables = consumables,
        hyperdriveRating = hyperdriveRating,
        mglt = mglt,
        starshipClass = starshipClass,
        created = created,
        edited = edited,
        url = url
    )
}

fun VehiclesDTO.toModel(): VehiclesModel {
    return VehiclesModel(
        name = name,
        model = model,
        manufacturer = manufacturer,
        costInCredits = costInCredits,
        length = length,
        maxAtmospheringSpeed = maxAtmospheringSpeed,
        crew = crew,
        passengers = passengers,
        cargoCapacity = cargoCapacity,
        consumables = consumables,
        vehicleClass = vehicleClass,
        created = created,
        edited = edited
    )
}