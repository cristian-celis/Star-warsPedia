package com.example.starwarspedia.domain.mappers

import com.example.starwarspedia.domain.modelsDto.Species
import com.example.starwarspedia.data.models.SpeciesModel

fun Species.toDomain(): SpeciesModel {
    return SpeciesModel(
        name = this.name,
        classification = this.classification,
        averageHeight = this.averageHeight,
        skinColors = this.skinColors,
        hairColors = this.hairColors,
        eyeColors = this.eyeColors,
        homeworld = this.homeworld,
        language = this.language,
        films = this.films,
        url = this.url
    )
}