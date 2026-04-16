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
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class RemoteRepository(val api: CharactersApi): IRemoteRepository {

    override suspend fun getAllCharacters(page: Int): CharactersPage {
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

                CharacterModel(
                    name = dto.name,
                    height = dto.height,
                    mass = dto.mass,
                    hairColor = dto.hairColor,
                    skinColor = dto.skinColor,
                    eyeColor = dto.eyeColor,
                    birthYear = dto.birthYear,
                    gender = dto.gender,
                    homeworld = homeworldDeferred.await(),
                    films = filmsDeferred.await(),
                    species = speciesDeferred.await(),
                    vehicles = vehiclesDeferred.await(),
                    starships = starshipsDeferred.await(),
                    created = dto.created,
                    edited = dto.edited,
                )
            }
        } catch (e: Exception) {
            logError(e)
            throw e
        }
    }

    override suspend fun getPlanet(planetId: String): PlanetModel? {
        return try {
            val dto = api.getPlanet(planetId)
            PlanetModel(
                name = dto.name,
                rotationPeriod = dto.rotationPeriod,
                orbitalPeriod = dto.orbitalPeriod,
                diameter = dto.diameter,
                climate = dto.climate,
                gravity = dto.gravity,
                terrain = dto.terrain,
                surfaceWater = dto.surfaceWater,
                population = dto.population,
                created = dto.created,
                edited = dto.edited,
                url = dto.url
            )
        } catch (e: Exception) {
            logError(e)
            null
        }
    }

    override suspend fun getStarship(starshipId: String): StarshipsModel? {
        return try {
            val dto = api.getStarship(starshipId)
            StarshipsModel(
                name = dto.name,
                model = dto.model,
                manufacturer = dto.manufacturer,
                costInCredits = dto.costInCredits,
                length = dto.length,
                maxAtmospheringSpeed = dto.maxAtmospheringSpeed,
                crew = dto.crew,
                passengers = dto.passengers,
                cargoCapacity = dto.cargoCapacity,
                consumables = dto.consumables,
                hyperdriveRating = dto.hyperdriveRating,
                mglt = dto.mglt,
                starshipClass = dto.starshipClass,
                created = dto.created,
                edited = dto.edited,
                url = dto.url,
            )
        } catch (e: Exception) {
            logError(e)
            null
        }
    }

    override suspend fun getFilm(filmId: String): FilmModel? {
        return try {
            val dto = api.getFilm(filmId)
            FilmModel(
                title = dto.title,
                episodeId = dto.episodeId,
                openingCrawl = dto.openingCrawl,
                director = dto.director,
                producer = dto.producer,
                releaseDate = dto.releaseDate,
                created = dto.created,
                edited = dto.edited
            )
        } catch (e: Exception) {
            logError(e)
            null
        }
    }

    override suspend fun getSpecies(speciesId: String): SpeciesModel? {
        return try {
            val dto = api.getSpecies(speciesId)
            SpeciesModel(
                name = dto.name,
                classification = dto.classification,
                designation = dto.designation,
                averageHeight = dto.averageHeight,
                skinColors = dto.skinColors,
                hairColors = dto.hairColors,
                eyeColors = dto.eyeColors,
                averageLifespan = dto.averageLifespan,
                homeworld = dto.homeworld,
                language = dto.language,
                created = dto.created,
                edited = dto.edited
            )
        } catch (e: Exception) {
            logError(e)
            null
        }
    }

    override suspend fun getVehicles(vehicleId: String): VehiclesModel? {
        return try {
            val dto = api.getVehicles(vehicleId)
            VehiclesModel(
                name = dto.name,
                model = dto.model,
                manufacturer = dto.manufacturer,
                costInCredits = dto.costInCredits,
                length = dto.length,
                maxAtmospheringSpeed = dto.maxAtmospheringSpeed,
                crew = dto.crew,
                passengers = dto.passengers,
                cargoCapacity = dto.cargoCapacity,
                consumables = dto.consumables,
                vehicleClass = dto.vehicleClass,
                created = dto.created,
                edited = dto.edited
            )
        } catch (e: Exception) {
            logError(e)
            null
        }
    }

    private fun extractIdFromUrl(url: String): String {
        return url.trimEnd('/').split("/").last()
    }
}