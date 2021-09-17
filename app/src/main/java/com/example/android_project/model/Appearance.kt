package com.example.android_project.model

import com.squareup.moshi.Json

data class Appearance (
    @Json(name = "gender")
    val gender: String,
    @Json(name = "race")
    val race: String,
    @Json(name = "height")
    val height: List<String>,
    @Json(name = "weight")
    val weight: List<String>,
    @Json(name = "eye-color")
    val eyeColor: String,
    @Json(name = "hair-color")
    val hairColor: String,
        )