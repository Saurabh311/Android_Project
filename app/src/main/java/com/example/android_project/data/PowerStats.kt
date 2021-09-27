package com.example.android_project.data

import com.squareup.moshi.Json
import java.io.Serializable

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
        ) : Serializable