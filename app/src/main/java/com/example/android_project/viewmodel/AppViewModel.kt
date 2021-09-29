package com.example.android_project.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import com.example.android_project.MAX_NUM_CHARSID
import com.example.android_project.data.Character
import com.example.android_project.data.CharacterList
import com.example.android_project.network.APIHelper
import kotlinx.coroutines.launch
import kotlin.random.Random

class AppViewModel : ViewModel() {
    private val apiHelper = APIHelper()

    private var character: MutableLiveData<Character> = MutableLiveData()
    private var characterList: MutableLiveData<CharacterList> = MutableLiveData()

    fun getCharacter(): LiveData<Character> {
        return character
    }

    fun getCharacterList(): LiveData<CharacterList> {
        return characterList
    }

    fun searchByName(name: String, context: Context) {
        viewModelScope.launch {
            val result = kotlin.runCatching {
                apiHelper.getCharByName(name)
            }.onSuccess { charResponse ->
                characterList.value = charResponse
            }.onFailure {
                Toast.makeText(context, "No result for '$name'", Toast.LENGTH_SHORT).show()
            }
            println(result)
        }
    }

    fun randomCharacter() {
        viewModelScope.launch {
            character.value = apiHelper.getCharById(Random.nextInt(1, MAX_NUM_CHARSID).toString())
        }
    }

    fun setCharacter(c: Character) {
        viewModelScope.launch {
            character.value = c
        }
    }
}
