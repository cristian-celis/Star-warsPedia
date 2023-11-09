package com.example.starwarspedia.domain.modelsDto

data class ListSpeciesDto(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Species>
)