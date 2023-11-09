package com.example.starwarspedia.domain.mappers

import com.example.starwarspedia.core.AdapterType
import com.example.starwarspedia.data.models.GenericModel
import com.example.starwarspedia.data.models.PeopleModel
import com.example.starwarspedia.data.models.PlanetsModel
import com.example.starwarspedia.data.models.SpeciesModel
import com.example.starwarspedia.domain.modelsDto.People
import com.example.starwarspedia.domain.modelsDto.Planets
import com.example.starwarspedia.domain.modelsDto.Species

fun People.toDomain2(): GenericModel{
    return GenericModel(
        name = this.name,
        firstData = this.homeworld,
        secondData = this.birthYear,
        url = this.url,
        modelType = AdapterType.PEOPLE
    )
}

fun Planets.toDomain2():GenericModel{
    return GenericModel(
        name = this.name,
        firstData = this.population,
        secondData = this.terrain,
        url = this.url,
        modelType = AdapterType.PLANETS
    )
}

fun Species.toDomain2(): GenericModel{
    return GenericModel(
        name = this.name,
        firstData = this.homeworld,
        secondData = this.language,
        url = this.url,
        modelType = AdapterType.SPECIES
    )
}

fun PeopleModel.toDomain(): GenericModel{
    return GenericModel(
        name = this.name,
        firstData = this.homeworld,
        secondData = this.birthYear,
        url = this.url,
        modelType = AdapterType.PEOPLE
    )
}

fun PlanetsModel.toDomain():GenericModel{
    return GenericModel(
        name = this.name,
        firstData = this.population,
        secondData = this.terrain,
        url = this.url,
        modelType = AdapterType.PLANETS
    )
}

fun SpeciesModel.toDomain(): GenericModel{
    return GenericModel(
        name = this.name,
        firstData = this.homeworld,
        secondData = this.language,
        url = this.url,
        modelType = AdapterType.SPECIES
    )
}

/*
fun People.toDomain(): PeopleModel {
    return PeopleModel(
        name = this.name,
        height = this.height,
        hairColor = this.hairColor,
        skinColor = this.skinColor,
        eyeColor = this.eyeColor,
        birthYear = this.birthYear,
        gender = this.gender,
        homeworld = this.homeworld,
        specie = this.species,
        films = this.films
    )
}
 */