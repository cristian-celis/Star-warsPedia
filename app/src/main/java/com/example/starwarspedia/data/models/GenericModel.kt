package com.example.starwarspedia.data.models

import com.example.starwarspedia.core.AdapterType

data class GenericModel(
    val name: String,
    var firstData: String?,
    var secondData: String?,
    val url: String,
    val modelType: AdapterType
)