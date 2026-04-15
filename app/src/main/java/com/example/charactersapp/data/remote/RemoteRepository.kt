package com.example.charactersapp.data.remote

import com.example.charactersapp.domain.model.Character
import com.example.charactersapp.domain.model.CharactersFeed
import com.example.charactersapp.domain.model.Planet
import com.example.charactersapp.domain.model.Starships
import com.example.charactersapp.domain.repository.Repository

class RemoteRepository(val api: CharactersApi): Repository {

    override fun getAllCharacters(): CharactersFeed {
        return api.getAllCharacters()
    }

    override fun getCharacter(peopleId: String): Character {
        return api.getCharacter(peopleId)
    }

    override fun getPlanet(planetId: String): Planet {
        return api.getPlanet(planetId)
    }

    override fun getStarship(starshipId: String): Starships {
        return api.getStarship(starshipId)
    }

    override fun getFilm(filmId: String): Starships {
        return api.getFilm(filmId)
    }
}