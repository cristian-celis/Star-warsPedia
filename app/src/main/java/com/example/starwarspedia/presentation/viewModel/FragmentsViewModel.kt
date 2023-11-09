package com.example.starwarspedia.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarspedia.data.RepositoryImpl
import com.example.starwarspedia.data.models.GenericModel
import com.example.starwarspedia.data.models.PeopleModel
import com.example.starwarspedia.data.models.PlanetsModel
import com.example.starwarspedia.data.models.SpeciesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FragmentsViewModel @Inject constructor(
    private val repositoryImpl: RepositoryImpl
) : ViewModel() {

    val genericList = MutableLiveData<List<GenericModel>>()

    val genericData = MutableLiveData<List<String>>()

    var loadedPeoplePages = 1;
    var loadedPlanetsPages = 1;
    var loadedSpeciesPages = 1;

    fun searchPeopleList(){
        if (repositoryImpl.searchSpeciesList().isNotEmpty()) genericList.postValue(repositoryImpl.searchPeopleList())
        else getPeopleList()
    }
    fun searchPlanetsList(){
        if (repositoryImpl.searchPlanetList().isNotEmpty()) genericList.postValue(repositoryImpl.searchPlanetList())
        else getPlanetsList()
    }
    fun searchSpeciesList(){
        if (repositoryImpl.searchSpeciesList().isNotEmpty()) genericList.postValue(repositoryImpl.searchSpeciesList())
        else getSpeciesList()
    }

    fun getPeopleList() {
        viewModelScope.launch {
            genericList.postValue(repositoryImpl.callPeopleList(loadedPeoplePages.toString()))
        }
        loadedPeoplePages++
    }
    fun getSpeciesList() {
        viewModelScope.launch {
            genericList.postValue(repositoryImpl.callSpeciesList(loadedSpeciesPages.toString()))
        }
        loadedSpeciesPages++
    }
    fun getPlanetsList() {
        viewModelScope.launch {
            genericList.postValue(repositoryImpl.callPlanetsList(loadedPlanetsPages.toString()))
        }
        loadedPlanetsPages++
    }

    fun getPerson(url: String): PeopleModel{
        return repositoryImpl.getPerson(url)
    }
    fun getPlanet(url: String): PlanetsModel{
        return repositoryImpl.getPlanet(url)
    }
    fun getSpecie(url: String): SpeciesModel{
        return repositoryImpl.getSpecie(url)
    }

    fun getFilms(UrlFilms: List<String>) {
        viewModelScope.launch {
            genericData.postValue(repositoryImpl.getFilms(UrlFilms))
        }
    }
}