package com.example.android_project.model

import com.squareup.moshi.Json

data class Character (
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "name")
    val name: String?= null,
    @Json(name = "powerstats")
    val powerStats: PowerStats? = null,
    @Json(name ="biography")
    val bio: Biography? = null,
    @Json(name = "appearance")
    val appearance: Appearance? = null,
    @Json(name = "work")
    val work: Work? = null,
    @Json(name = "connections")
    val connections: Connections? = null,
    @Json(name = "image")
    val img: Thumbnail?= null,



    var wins: Int? = null,
    var loss: Int? = null,


        )



