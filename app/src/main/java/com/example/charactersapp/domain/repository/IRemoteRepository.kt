package com.example.charactersapp.domain.repository

import com.example.charactersapp.data.remote.paging.CharactersPage
import com.example.charactersapp.domain.model.CharacterModel
import com.example.charactersapp.domain.model.PlanetModel
import com.example.charactersapp.domain.model.SpeciesModel
import com.example.charactersapp.domain.model.StarshipsModel
import com.example.charactersapp.domain.model.VehiclesModel
import com.example.charactersapp.domain.model.FilmModel

interface IRemoteRepository {
    fun getAllCharacters(page: Int): CharactersPage
    fun getCharacter(peopleId: String): CharacterModel
    fun getPlanet(planetId: String): PlanetModel
    fun getStarship(starshipId: String): StarshipsModel
    fun getFilm(filmId: String): FilmModel
    fun getSpecies(speciesId: String): SpeciesModel
    fun getVehicles(vehicleId: String): VehiclesModel
}