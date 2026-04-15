package com.example.charactersapp.data.remote

import com.example.charactersapp.data.remote.dto.Character
import com.example.charactersapp.data.remote.dto.CharactersFeed
import com.example.charactersapp.data.remote.dto.Film
import com.example.charactersapp.data.remote.dto.Planet
import com.example.charactersapp.data.remote.dto.Species
import com.example.charactersapp.data.remote.dto.Starships
import com.example.charactersapp.data.remote.dto.Vehicles
import com.example.charactersapp.domain.repository.IRemoteRepository

class RemoteRepository(val api: CharactersApi): IRemoteRepository {

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

    override fun getFilm(filmId: String): Film {
        return api.getFilm(filmId)
    }

    override fun getSpecies(speciesId: String): Species {
        return api.getSpecies(speciesId)
    }

    override fun getVehicles(vehicleId: String): Vehicles {
        return api.getVehicles(vehicleId)
    }
}