package com.example.charactersapp.data.remote


import com.example.charactersapp.data.remote.paging.CharactersPage
import com.example.charactersapp.domain.model.CharacterFeedModel
import com.example.charactersapp.domain.model.CharacterModel
import com.example.charactersapp.domain.model.FilmModel
import com.example.charactersapp.domain.model.PlanetModel
import com.example.charactersapp.domain.model.SpeciesModel
import com.example.charactersapp.domain.model.StarshipsModel
import com.example.charactersapp.domain.model.VehiclesModel
import com.example.charactersapp.domain.repository.IRemoteRepository
import com.example.charactersapp.presentation.utils.logError

class RemoteRepository(val api: CharactersApi): IRemoteRepository {

    override fun getAllCharacters(page: Int): CharactersPage {
        val response = api.getAllCharacters(page)

        return try {
            CharactersPage(
                characters = response.results.map { dto ->
                    CharacterFeedModel(
                        name = dto.name,
                        height = dto.height,
                        mass = dto.mass,
                        hairColor = dto.hairColor,
                        skinColor = dto.skinColor,
                        eyeColor = dto.eyeColor,
                        birthYear = dto.birthYear,
                        gender = dto.gender
                    )
                },
                hasNextPage = response.next != null,
                hasPreviousPage = response.previous != null,
                isSuccess = true
            )
        } catch (e: Exception) {
            logError(e)

            CharactersPage(
                characters = emptyList(),
                hasNextPage = false,
                hasPreviousPage = false,
                isSuccess = false
            )
        }
    }

    override fun getCharacter(peopleId: String): CharacterModel {
        return api.getCharacter(peopleId)
    }

    override fun getPlanet(planetId: String): PlanetModel {
        return api.getPlanet(planetId)
    }

    override fun getStarship(starshipId: String): StarshipsModel {
        return api.getStarship(starshipId)
    }

    override fun getFilm(filmId: String): FilmModel {
        return api.getFilm(filmId)
    }

    override fun getSpecies(speciesId: String): SpeciesModel {
        return api.getSpecies(speciesId)
    }

    override fun getVehicles(vehicleId: String): VehiclesModel {
        return api.getVehicles(vehicleId)
    }
}