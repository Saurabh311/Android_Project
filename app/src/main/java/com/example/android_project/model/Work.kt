package com.example.android_project.model

import com.squareup.moshi.Json
import java.io.Serializable

data class Work (
    @Json(name = "occupation")
    val occupation: String,
    @Json(name = "base")
    val base: String
        ) : Serializable