package com.example.android_project.network

import com.example.android_project.data.Character
import com.example.android_project.data.CharacterList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

object APIClient {

    private const val API_KEY = "10159078203192626/"

    private const val BASE_URL = "https://superheroapi.com/api/"

    private val moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    private val retroFit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL + API_KEY)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val apiService: ApiService by lazy {
        retroFit.create(ApiService::class.java)
    }
}

interface ApiService {
    @GET("search/{name}")
    suspend fun fetchCharacterByName(
        @Path("name") name: String
    ): CharacterList

    @GET("{id}")
    suspend fun fetchCharacterById(
        @Path("id") id: String
    ): Character
}