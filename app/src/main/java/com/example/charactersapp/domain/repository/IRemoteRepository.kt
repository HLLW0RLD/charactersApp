package com.example.charactersapp.domain.repository

import com.example.charactersapp.data.remote.dto.Character
import com.example.charactersapp.data.remote.dto.CharactersFeed
import com.example.charactersapp.data.remote.dto.Film
import com.example.charactersapp.data.remote.dto.Planet
import com.example.charactersapp.data.remote.dto.Species
import com.example.charactersapp.data.remote.dto.Starships
import com.example.charactersapp.data.remote.dto.Vehicles
import retrofit2.http.GET
import retrofit2.http.Path

interface IRemoteRepository {
    fun getAllCharacters(): CharactersFeed
    fun getCharacter(peopleId: String): Character
    fun getPlanet(planetId: String): Planet
    fun getStarship(starshipId: String): Starships
    fun getFilm(filmId: String): Film
    fun getSpecies(speciesId: String): Species
    fun getVehicles(vehicleId: String): Vehicles
}