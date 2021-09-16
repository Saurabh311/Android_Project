package com.example.android_project.model

import com.squareup.moshi.Json

data class CharacterResponse(
    @Json(name = "response")
    val response: String? = null
)