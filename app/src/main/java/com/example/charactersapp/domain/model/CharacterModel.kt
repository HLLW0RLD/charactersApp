package com.example.charactersapp.domain.model


data class CharacterModel(
    val name: String,
    val height: String,
    val mass: String,
    val hairColor: String,
    val skinColor: String,
    val eyeColor: String,
    val birthYear: String,
    val gender: String,
    val homeworld: PlanetModel,
    val created: String,
    val edited: String,

    val films: List<FilmModel>,
    val species: List<SpeciesModel>,
    val vehicles: List<VehiclesModel>,
    val starships: List<StarshipsModel>,
)