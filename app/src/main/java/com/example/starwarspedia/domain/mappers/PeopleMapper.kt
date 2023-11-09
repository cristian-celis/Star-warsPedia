package com.example.starwarspedia.domain.mappers

import com.example.starwarspedia.domain.modelsDto.People
import com.example.starwarspedia.data.models.PeopleModel

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
        films = this.films,
        url = this.url
    )
}