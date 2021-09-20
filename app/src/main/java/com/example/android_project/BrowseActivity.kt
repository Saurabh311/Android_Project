package com.example.android_project

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_project.adapter.RecyclerAdapter
import com.example.android_project.model.CharacterResponse
import com.example.android_project.network.APIclient
import retrofit2.Call
import retrofit2.Response

lateinit var et_searchHero: EditText
lateinit var btn_searchHero: Button

class BrowseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)


        //TODO("retrieve data and send to RecyclerAdapter(this, -data-)")

    }


    fun searchForHero(view: View) {

        et_searchHero = findViewById(R.id.et_heroSearch)
        val query = et_searchHero.text.toString()

        //Hides the keyboard when user clicks search btn
        val view = this.currentFocus
        et_searchHero.clearFocus()
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        if (query != "") {
            et_searchHero.text.clear()
            getHeroes(query)

        } else {
            Toast.makeText(
                this,
                "Enter at least one character to search for heroes",
                Toast.LENGTH_SHORT
            ).show()
        }


    }

    fun getHeroes(query: String) {
        val client = APIclient.apiService.fetchCharacterByName(query)
        client.enqueue(object : retrofit2.Callback<CharacterResponse> {
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if (response.isSuccessful) {

                    val result = response.body()?.results
                    //Log.d("Character: ", "" + result)

                    result?.let {
                        val adapter = RecyclerAdapter(result, this@BrowseActivity)
                        val recyclerView: RecyclerView = findViewById(R.id.rv_heroList)
                        recyclerView.adapter = adapter
                        recyclerView?.layoutManager = LinearLayoutManager(this@BrowseActivity)
                    }
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e("failed", "" + t.message)
            }
        })
    }






}
