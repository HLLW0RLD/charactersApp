package com.example.charactersapp.data.remote

import com.example.charactersapp.data.remote.dto.Character
import com.example.charactersapp.data.remote.dto.CharactersFeed
import com.example.charactersapp.data.remote.dto.Film
import com.example.charactersapp.data.remote.dto.Planet
import com.example.charactersapp.data.remote.dto.Species
import com.example.charactersapp.data.remote.dto.Starships
import com.example.charactersapp.data.remote.dto.Vehicles
import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersApi {

    @GET("people/")
    fun getAllCharacters(): CharactersFeed

    @GET("people/{peopleId}")
    fun getCharacter(@Path("peopleId") peopleId: String): Character

    @GET("planets/{planetId}")
    fun getPlanet(@Path("planetId") planetId: String): Planet

    @GET("starships/{starshipId}")
    fun getStarship(@Path("starshipId") starshipId: String): Starships

    @GET("films/{filmId}")
    fun getFilm(@Path("filmId") filmId: String): Film

    @GET("species/{speciesId}")
    fun getSpecies(@Path("speciesId") speciesId: String): Species

    @GET("vehicles/{vehicleId}")
    fun getVehicles(@Path("vehicleId") vehicleId: String): Vehicles
}