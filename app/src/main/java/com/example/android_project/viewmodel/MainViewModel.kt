package com.example.android_project.viewmodel

import android.content.Context
import androidx.lifecycle.*
import com.example.android_project.MAX_NUM_CHARSID
import com.example.android_project.model.Character
import com.example.android_project.model.CharacterResponse
import com.example.android_project.network.APIhelper
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel : ViewModel(){
    private val apihelper = APIhelper()

    private var character: MutableLiveData<Character> = MutableLiveData()
    private var characterList: MutableLiveData<CharacterResponse> = MutableLiveData()

    fun getCharacter(): LiveData<Character> {
        return character
    }

    fun getCharacterList(): LiveData<CharacterResponse> {
        return characterList
    }

    fun searchById(id: String) {
        viewModelScope.launch {
            character.value = apihelper.getCharById(id)
        }
    }

    fun searchByName(name: String, context: Context) {
        viewModelScope.launch {
            characterList = apihelper.getCharByName(name, context)
        }
    }

    fun randomCharacter() {
        viewModelScope.launch {
            character.value = apihelper.getCharById(Random.nextInt(1, MAX_NUM_CHARSID).toString())
        }
    }
}
