package com.example.starwarspedia.data.models

data class SpeciesModel(
    val name: String,
    val classification: String,
    val averageHeight: String,
    val skinColors: String,
    val hairColors: String,
    val eyeColors: String,
    val homeworld: String?,
    val language: String,
    val films: List<String>,
    val url: String
    /*
    "name": "Human",
            "classification": "mammal",
            "average_height": "180",
            "skin_colors": "caucasian, black, asian, hispanic",
            "hair_colors": "blonde, brown, black, red",
            "eye_colors": "brown, blue, green, hazel, grey, amber",
            "homeworld": "https://swapi.dev/api/planets/9/",
            "language": "Galactic Basic",
            "films": [
                "https://swapi.dev/api/films/1/",
                "https://swapi.dev/api/films/2/",
                "https://swapi.dev/api/films/3/",
                "https://swapi.dev/api/films/4/",
                "https://swapi.dev/api/films/5/",
                "https://swapi.dev/api/films/6/"
            ],
     */
)