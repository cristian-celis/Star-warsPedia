package com.example.starwarspedia.data

import android.util.Log
import com.example.starwarspedia.data.models.AllModels
import com.example.starwarspedia.data.models.GenericModel
import com.example.starwarspedia.domain.mappers.toDomain
import com.example.starwarspedia.domain.retrofit.APIService
import com.example.starwarspedia.data.models.PeopleModel
import com.example.starwarspedia.data.models.PlanetsModel
import com.example.starwarspedia.data.models.SpeciesModel
import com.example.starwarspedia.domain.mappers.toDomain2
import retrofit2.Retrofit
import java.lang.NullPointerException
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val retrofit: Retrofit,
    private val allList: AllModels
) {

    private val planetsLoaded = HashMap<String, String>()
    private val filmsLoaded = HashMap<String, String>()
    private val speciesLoaded = HashMap<String, String>()

    fun searchPeopleList(): List<GenericModel>{
        return allList.peopleList.map { it.toDomain() }
    }
    fun searchPlanetList(): List<GenericModel>{
        return allList.planetsList.map { it.toDomain() }
    }
    fun searchSpeciesList(): List<GenericModel>{
        return allList.speciesList.map { it.toDomain() }
    }

    suspend fun callPeopleList(query: String): List<GenericModel> {
        try {
            val call = retrofit.create(APIService::class.java).getPeople("people/?page=$query")
            val peopleObj = call.body()

            return if (call.isSuccessful) {
                for (i in 0 until peopleObj!!.results.size)
                    peopleObj.results[i].homeworld = getOnlyAPlanet(peopleObj.results[i].homeworld)
                allList.peopleList.addAll(peopleObj.results.map { it.toDomain() })
                peopleObj.results.map { it.toDomain2() }
            } else {
                emptyList()
            }

        }catch (e: Exception) {
            Log.e("mitg", "Exception People: ${e.message}")
        }
        return emptyList()
    }

    fun getPerson(url: String): PeopleModel{
        allList.peopleList.forEach {
            if (it.url == url) return it
        }
        throw NullPointerException("ERROR: People Object not found")
    }
    fun getPlanet(url: String): PlanetsModel{
        allList.planetsList.forEach {
            if (it.url == url) return it
        }
        throw NullPointerException("ERROR: Planet Object not found")
    }
    fun getSpecie(url: String): SpeciesModel{
        allList.speciesList.forEach {
            if (it.url == url) return it
        }
        throw NullPointerException("ERROR: Specie Object not found")
    }

    suspend fun callPlanetsList(query: String): List<GenericModel> {
        try {
            val call = retrofit.create(APIService::class.java).getPlanets("planets/?page=$query")
            val planetsObj = call.body()
            return if(call.isSuccessful){
                allList.planetsList.addAll(planetsObj!!.results.map { it.toDomain() })
                planetsObj.results.map { it.toDomain2() }
            }
            else emptyList()

        } catch (e: Exception) {
            Log.e("mitg", "Exception Planet: ${e.message}")
        }
        return emptyList()
    }

    suspend fun callSpeciesList(query: String): List<GenericModel> {
        try {
            val call = retrofit.create(APIService::class.java).getSpecies("species/?page=$query")
            val speciesObj = call.body()

            return if (call.isSuccessful) {
                for (i in 0 until speciesObj!!.results.size)
                    if (speciesObj.results[i].homeworld != null) speciesObj.results[i].homeworld =
                        getOnlyAPlanet(speciesObj.results[i].homeworld)
                allList.speciesList.addAll(speciesObj.results.map { it.toDomain() })
                speciesObj.results.map { it.toDomain2() }
            } else emptyList()

        } catch (e: Exception) {
            Log.e("mitg", "Exception Species: ${e.message}")
        }
        return emptyList()
    }

    private suspend fun getOnlyAPlanet(Url: String): String {
        if (planetsLoaded.containsKey(Url)) {
            return planetsLoaded[Url]!!
        } else {
            try {
                val call = retrofit.create(APIService::class.java).getPlanet(Url)
                if (call.isSuccessful) {
                    planetsLoaded[Url] = call.body()!!.name
                    return call.body()!!.name
                }

            } catch (e: Exception) {
                Log.e("mitg", "Exception Film: ${e.message}")
            }
            return ""
        }
    }
    suspend fun getFilms(UrlFilms: List<String>): MutableList<String> {
        val films: MutableList<String> = mutableListOf()
        UrlFilms.forEach {
            films.add(getOnlyAFilm(it))
        }
        return films
    }

    private suspend fun getOnlyAFilm(Url: String): String {
        if (filmsLoaded.containsKey(Url)) {
            return filmsLoaded[Url]!!
        } else {
            try {
                val call = retrofit.create(APIService::class.java).getFilm(Url)
                if (call.isSuccessful) {
                    filmsLoaded[Url] = call.body()!!.title
                    return call.body()!!.title
                }

            } catch (e: Exception) {
                Log.e("mitg", "Exception Film: ${e.message}")
            }
            return ""
        }
    }
}