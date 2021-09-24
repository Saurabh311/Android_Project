package com.example.android_project.network


class APIhelper {
    var client = APIclient.apiService

    suspend fun getCharById(id: String) = client.fetchCharacterById(id)

    suspend fun getCharByName(name: String) = client.fetchCharacterByName(name)

}