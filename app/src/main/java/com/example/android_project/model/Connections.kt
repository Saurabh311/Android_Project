package com.example.android_project.model

import com.squareup.moshi.Json

data class Connections (
    @Json(name = "group-affiliation")
    val affiliation: String,
    @Json(name = "relatives")
    val relatives: String
        )