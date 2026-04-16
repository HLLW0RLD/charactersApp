package com.example.charactersapp.data.remote

import com.example.charactersapp.data.remote.dto.CharacterDTO
import com.example.charactersapp.data.remote.dto.CharactersPagingData
import com.example.charactersapp.data.remote.dto.FilmDTO
import com.example.charactersapp.data.remote.dto.PlanetDTO
import com.example.charactersapp.data.remote.dto.SpeciesDTO
import com.example.charactersapp.data.remote.dto.StarshipsDTO
import com.example.charactersapp.data.remote.dto.VehiclesDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApi {

    @GET("people/")
    suspend fun getAllCharacters(@Query("page") page: Int? = null): CharactersPagingData

    @GET("people/{peopleId}")
    suspend fun getCharacter(@Path("peopleId") peopleId: String): CharacterDTO

    @GET("planets/{planetId}")
    suspend fun getPlanet(@Path("planetId") planetId: String): PlanetDTO

    @GET("starships/{starshipId}")
    suspend fun getStarship(@Path("starshipId") starshipId: String): StarshipsDTO

    @GET("films/{filmId}")
    suspend fun getFilm(@Path("filmId") filmId: String): FilmDTO

    @GET("species/{speciesId}")
    suspend fun getSpecies(@Path("speciesId") speciesId: String): SpeciesDTO

    @GET("vehicles/{vehicleId}")
    suspend fun getVehicles(@Path("vehicleId") vehicleId: String): VehiclesDTO
}