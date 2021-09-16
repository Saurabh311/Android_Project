package com.example.android_project.Model
import com.squareup.moshi.Json
data class Character (
    @Json(name = "charid")
    val charId: Int,
    var wins: Int,
    var loss: Int


        )

