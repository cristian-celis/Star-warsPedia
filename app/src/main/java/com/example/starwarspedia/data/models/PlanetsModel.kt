package com.example.starwarspedia.data.models

data class PlanetsModel(
    val name: String,
    val rotationPeriod: String,
    val orbitalPeriod: String,
    val diameter: String,
    val gravity: String,
    val terrain: String,
    val population: String,
    val films: List<String>,
    val url: String
    /*
     "name": "Tatooine",
    "rotation_period": "23",
    "orbital_period": "304",
    "diameter": "10465",
    "gravity": "1 standard",
    "terrain": "desert",
    "population": "200000",
    "films": [
        "https://swapi.dev/api/films/1/",
        "https://swapi.dev/api/films/3/",
        "https://swapi.dev/api/films/4/",
        "https://swapi.dev/api/films/5/",
        "https://swapi.dev/api/films/6/"
    ],
     */
)