package com.example.starwarspedia.domain.mappers

import com.example.starwarspedia.domain.modelsDto.Planets
import com.example.starwarspedia.data.models.PlanetsModel

fun Planets.toDomain(): PlanetsModel {
    return PlanetsModel(
        name = this.name,
        rotationPeriod = this.rotationPeriod,
        orbitalPeriod = this.orbitalPeriod,
        diameter = this.diameter,
        gravity = this.gravity,
        terrain = this.terrain,
        population = this.population,
        films = this.films,
        url = this.url
    )
}