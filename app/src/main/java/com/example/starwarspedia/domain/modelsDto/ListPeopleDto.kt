package com.example.starwarspedia.domain.modelsDto

data class ListPeopleDto(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<People>
)