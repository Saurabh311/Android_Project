package com.example.android_project.network

import com.example.android_project.model.CharacterResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object APIclient {

    const val API_KEY = "10159078203192626/"

    const val BASE_URL = "https://superheroapi.com/api/"


    private val moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    private val retroFit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val apiService: ApiService by lazy {
        retroFit.create(ApiService::class.java)
    }
}

interface ApiService {
    @GET("10159078203192626")
    fun fetchCharactersById(
       @GET / @Path(null)id: String) : Call<CharacterResponse>
    @GET("search")
    fun fetchCharacterByName(
        @Query("name") name: String) : Call<CharacterResponse>
}