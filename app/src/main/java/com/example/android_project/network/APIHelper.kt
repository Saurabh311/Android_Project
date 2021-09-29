package com.example.android_project.network


class APIHelper {
    private var client = APIClient.apiService

    suspend fun getCharById(id: String) = client.fetchCharacterById(id)

    suspend fun getCharByName(name: String) = client.fetchCharacterByName(name)

}