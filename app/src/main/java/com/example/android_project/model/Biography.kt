package com.example.android_project.model

import com.squareup.moshi.Json

data class Biography (
    @Json(name = "full-name")
    val fullName: String,
    @Json(name = "alter-egos")
    val alterEgo: String,
    @Json(name = "aliases")
    val aliases: List<String>,
    @Json(name = "publisher")
    val publisher: String,
    @Json(name = "alignment")
    val alignment: String,
        )