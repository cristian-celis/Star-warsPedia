package com.example.starwarspedia.data.models

import com.example.starwarspedia.data.models.PeopleModel
import com.example.starwarspedia.data.models.PlanetsModel
import com.example.starwarspedia.data.models.SpeciesModel

class AllModels(
    var peopleList: MutableList<PeopleModel>,
    var planetsList: MutableList<PlanetsModel>,
    var speciesList: MutableList<SpeciesModel>
)