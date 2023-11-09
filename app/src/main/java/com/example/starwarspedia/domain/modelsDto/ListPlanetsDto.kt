package com.example.starwarspedia.domain.modelsDto

data class ListPlanetsDto(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Planets>
)