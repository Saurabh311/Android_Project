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

    fun getCharById(id: String): LiveData<Character> {
        val liveData = MutableLiveData<Character>()

        client.fetchCharacterById(id).enqueue(object : retrofit2.Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                liveData.value = response.body()
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return liveData
    }

    fun getCharByName(name: String, context: Context): LiveData<CharacterResponse> {
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