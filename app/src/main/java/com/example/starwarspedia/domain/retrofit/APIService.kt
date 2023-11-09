package com.example.starwarspedia.domain.retrofit

import com.example.starwarspedia.domain.modelsDto.Films
import com.example.starwarspedia.domain.modelsDto.ListPeopleDto
import com.example.starwarspedia.domain.modelsDto.ListPlanetsDto
import com.example.starwarspedia.domain.modelsDto.ListSpeciesDto
import com.example.starwarspedia.domain.modelsDto.People
import com.example.starwarspedia.domain.modelsDto.Planets
import com.example.starwarspedia.domain.modelsDto.Species
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getPeople(@Url url: String): Response<ListPeopleDto>

    @GET
    suspend fun getSpecies(@Url url: String): Response<ListSpeciesDto>

    @GET
    suspend fun getPlanets(@Url url: String): Response<ListPlanetsDto>

    @GET
    suspend fun getPerson(@Url url: String): Response<People>

    @GET
    suspend fun getSpecie(@Url url: String): Response<Species>

    @GET
    suspend fun getPlanet(@Url url: String): Response<Planets>

    @GET
    suspend fun getFilm(@Url url: String): Response<Films>
}