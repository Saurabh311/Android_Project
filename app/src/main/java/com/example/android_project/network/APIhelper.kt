package com.example.android_project.network

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Response
import com.example.android_project.model.Character
import com.example.android_project.model.CharacterResponse

class APIhelper {
    var client = APIclient.apiService

    suspend fun getCharById(id: String) = client.fetchCharacterById(id)

    fun getCharByName(name: String, context: Context): MutableLiveData<CharacterResponse> {
        val liveData = MutableLiveData<CharacterResponse>()

        client.fetchCharacterByName(name).enqueue(object : retrofit2.Callback<CharacterResponse> {
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                liveData.value = response.body()
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Toast.makeText(context, "No result for '$name'", Toast.LENGTH_SHORT).show()
            }
        })
        return liveData
    }
}