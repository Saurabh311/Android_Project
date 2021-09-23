package com.example.android_project.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Response
import com.example.android_project.model.Character
import com.example.android_project.model.CharacterResponse

class APIhelper {
    var client = APIclient.apiService

    suspend fun getCharById(id: String) = client.fetchCharacterById(id)

    suspend fun getCharByName(name: String) = client.fetchCharacterByName(name)
}