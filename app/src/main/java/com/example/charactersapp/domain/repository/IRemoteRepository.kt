package com.example.charactersapp.domain.repository

import com.example.charactersapp.data.remote.paging.CharactersPage
import com.example.charactersapp.domain.model.CharacterModel
import com.example.charactersapp.domain.model.PlanetModel
import com.example.charactersapp.domain.model.SpeciesModel
import com.example.charactersapp.domain.model.StarshipsModel
import com.example.charactersapp.domain.model.VehiclesModel
import com.example.charactersapp.domain.model.FilmModel

interface IRemoteRepository {
    suspend fun getAllCharacters(page: Int): CharactersPage
    suspend fun getCharacter(peopleId: String): CharacterModel?
    suspend fun getPlanet(planetId: String): PlanetModel?
    suspend fun getStarship(starshipId: String): StarshipsModel?
    suspend fun getFilm(filmId: String): FilmModel?
    suspend fun getSpecies(speciesId: String): SpeciesModel?
    suspend fun getVehicles(vehicleId: String): VehiclesModel?
}