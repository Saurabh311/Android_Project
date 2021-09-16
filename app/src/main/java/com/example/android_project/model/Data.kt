package com.example.android_project.model

import com.squareup.moshi.Json

class Data (
        @Json(name = "response")
        val response: List<String>
        )