package com.example.charactersapp.data.local

import com.example.charactersapp.domain.model.Character
import com.example.charactersapp.domain.model.CharactersFeed
import com.example.charactersapp.domain.model.Planet
import com.example.charactersapp.domain.model.Starships
import com.example.charactersapp.domain.repository.Repository

class LocalRepository(val dao: CharactersDao): Repository {

    override fun getAllCharacters(): CharactersFeed {
        return dao.getAllCharacters()
    }

    override fun getCharacter(peopleId: String): Character {
        return dao.getCharacter(peopleId)
    }

    override fun getPlanet(planetId: String): Planet {
        return dao.getPlanet(planetId)
    }

    override fun getStarship(starshipId: String): Starships {
        return dao.getStarship(starshipId)
    }

    override fun getFilm(filmId: String): Starships {
        return dao.getFilm(filmId)
    }
}