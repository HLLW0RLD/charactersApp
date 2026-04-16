package com.example.charactersapp.data.remote.repository

import com.example.charactersapp.data.converters.toBaseCharacterModel
import com.example.charactersapp.data.converters.toFeedModel
import com.example.charactersapp.data.converters.toModel
import com.example.charactersapp.data.paging.CharactersPage
import com.example.charactersapp.data.remote.api.CharactersApi
import com.example.charactersapp.domain.model.CharacterModel
import com.example.charactersapp.domain.model.FilmModel
import com.example.charactersapp.domain.model.PlanetModel
import com.example.charactersapp.domain.model.SpeciesModel
import com.example.charactersapp.domain.model.StarshipsModel
import com.example.charactersapp.domain.model.VehiclesModel
import com.example.charactersapp.domain.repository.IRemoteRepository
import com.example.charactersapp.presentation.utils.logError
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class RemoteRepository(val api: CharactersApi): IRemoteRepository {

    override suspend fun getAllCharacters(page: Int): CharactersPage {
        val response = api.getAllCharacters(page)

        return try {
            CharactersPage(
                characters = response.results.map { it.toFeedModel(extractIdFromUrl(it.url)) },
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

    override suspend fun getCharacter(peopleId: String): CharacterModel {
        return try {
            val dto = api.getCharacter(peopleId)

            coroutineScope {
                val homeworldDeferred = async {
                    try {
                        getPlanet(extractIdFromUrl(dto.homeworld))
                    } catch (e: Exception) {
                        logError(e)
                        null
                    }
                }

                val filmsDeferred = async {
                    dto.films.mapNotNull { filmUrl ->
                        try {
                            getFilm(extractIdFromUrl(filmUrl))
                        } catch (e: Exception) {
                            logError(e)
                            null
                        }
                    }
                }

                val speciesDeferred = async {
                    dto.species.mapNotNull { speciesUrl ->
                        try {
                            getSpecies(extractIdFromUrl(speciesUrl))
                        } catch (e: Exception) {
                            logError(e)
                            null
                        }
                    }
                }

                val vehiclesDeferred = async {
                    dto.vehicles.mapNotNull { vehicleUrl ->
                        try {
                            getVehicles(extractIdFromUrl(vehicleUrl))
                        } catch (e: Exception) {
                            logError(e)
                            null
                        }
                    }
                }

                val starshipsDeferred = async {
                    dto.starships.mapNotNull { starshipUrl ->
                        try {
                            getStarship(extractIdFromUrl(starshipUrl))
                        } catch (e: Exception) {
                            logError(e)
                            null
                        }
                    }
                }

                dto.toBaseCharacterModel(
                    homeworld = homeworldDeferred.await(),
                    films = filmsDeferred.await(),
                    species = speciesDeferred.await(),
                    vehicles = vehiclesDeferred.await(),
                    starships = starshipsDeferred.await()
                )
            }
        } catch (e: Exception) {
            logError(e)
            throw e
        }
    }

    override suspend fun getPlanet(planetId: String): PlanetModel? {
        return try {
            api.getPlanet(planetId).toModel()
        } catch (e: Exception) {
            logError(e)
            null
        }
    }

    override suspend fun getStarship(starshipId: String): StarshipsModel? {
        return try {
            api.getStarship(starshipId).toModel()
        } catch (e: Exception) {
            logError(e)
            null
        }
    }

    override suspend fun getFilm(filmId: String): FilmModel? {
        return try {
            api.getFilm(filmId).toModel()
        } catch (e: Exception) {
            logError(e)
            null
        }
    }

    override suspend fun getSpecies(speciesId: String): SpeciesModel? {
        return try {
            api.getSpecies(speciesId).toModel()
        } catch (e: Exception) {
            logError(e)
            null
        }
    }

    override suspend fun getVehicles(vehicleId: String): VehiclesModel? {
        return try {
            api.getVehicles(vehicleId).toModel()
        } catch (e: Exception) {
            logError(e)
            null
        }
    }

    private fun extractIdFromUrl(url: String): String {
        return url.trimEnd('/').split("/").last()
    }
}