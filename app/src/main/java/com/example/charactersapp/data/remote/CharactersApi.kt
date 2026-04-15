package com.example.charactersapp.data.remote

import com.example.charactersapp.domain.model.Character
import com.example.charactersapp.domain.model.CharactersFeed
import com.example.charactersapp.domain.model.Planet
import com.example.charactersapp.domain.model.Starships
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
    fun getFilm(@Path("filmId") filmId: String): Starships
}