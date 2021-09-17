package com.example.android_project.model

import com.squareup.moshi.Json

data class Character (
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "name")
    val name: String?= null,
    @Json(name = "image")
    val img: Img?= null,



    var wins: Int? = null,
    var loss: Int? = null,


        )

data class Img (
    val url: String,
        )



