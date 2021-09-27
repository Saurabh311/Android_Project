package com.example.android_project.data

import com.squareup.moshi.Json
import java.io.Serializable

data class Connections (
    @Json(name = "group-affiliation")
    val affiliation: String,
    @Json(name = "relatives")
    val relatives: String
        ) : Serializable