package com.example.android_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = RecyclerAdapter(this)
        //TODO("retrieve data and send to RecyclerAdapter(this, -data-)")

    }


    fun searchForHero(view: View) {

        et_searchHero = findViewById(R.id.et_heroSearch)

        val query = et_searchHero.text.toString()
        if(query != "") {
            et_searchHero.text.clear()
            //APIclient.searchHeroByName(query)
        } else {
            Toast.makeText(this, "Enter at least one character to search for heroes", Toast.LENGTH_SHORT).show()
        }





    }
}
