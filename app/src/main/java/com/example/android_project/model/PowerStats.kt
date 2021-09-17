package com.example.android_project.model

import com.squareup.moshi.Json

data class PowerStats (
    @Json(name = "intelligence")
    val intelligence: String,
    @Json(name = "strength")
    val strength: String,
    @Json(name = "speed")
    val speed: String,
    @Json(name = "durability")
    val durability: String,
    @Json(name = "power")
    val power: String,
    @Json(name = "combat")
    val combat: String,
        )