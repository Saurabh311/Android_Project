package com.example.android_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.example.android_project.Database.CharScoreDatabaseHelper
import com.example.android_project.Model.Character
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //temp code start fredrik
        val image_url = "https://lumiere-a.akamaihd.net/v1/images/p_avengersendgame_19751_e14a0104.jpeg?region=0,0,540,810&width=480"
        val imageView = findViewById<ImageView>(R.id.characterPic)
        Picasso.get().load(image_url).into(imageView)

        //temp code Oskar
        startActivity(Intent(this, GameActivity::class.java))


    }
    /*
    methods that shows how to add character to database and getting lose/wins from a list
    this can be removed when we want //fredrik

    fun addCharScore (view: View){
        val dbHelper = CharScoreDatabaseHelper (this)
         val charid = findViewById<EditText>(R.id.charid).text.toString()
        val wins = findViewById<EditText>(R.id.winid).text.toString()
        val loss = findViewById<EditText>(R.id.lossid).text.toString()

        try {
            val char = Character(charid.toInt(), wins.toInt(), loss.toInt())
            dbHelper.addCharScore(char)

        }catch (e:Exception){
            println("character was not modified/added")
        }


    }
    fun check (view: View){
        val dbHelper = CharScoreDatabaseHelper (this)
        var listchar = mutableListOf<Character>()
        val char1 = Character(1,2,3)
        val char2 = Character(2,3,4)
        listchar.add(char1)
        listchar.add(char2)
        val list  = dbHelper.getAllCharScores(listchar)
        println(list.get(0).wins)
        println(list.size)
    }
     */
}