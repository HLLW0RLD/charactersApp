package com.example.charactersapp.domain.repository

import com.example.charactersapp.domain.model.Character
import com.example.charactersapp.domain.model.CharactersFeed
import com.example.charactersapp.domain.model.Planet
import com.example.charactersapp.domain.model.Starships
import retrofit2.http.Path

interface Repository {
    fun getAllCharacters(): CharactersFeed
    fun getCharacter(peopleId: String): Character
    fun getPlanet(planetId: String): Planet
    fun getStarship(starshipId: String): Starships
    fun getFilm(filmId: String): Starships
}