package com.example.android_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.squareup.picasso.Picasso


lateinit var tv_heroName1: TextView
lateinit var tv_heroName2: TextView
lateinit var btn_heroInfo1: Button
lateinit var btn_heroInfo2: Button
lateinit var cv_hero2: CardView
class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)






    }

    fun selectHero(view: View) {
        val id = view.id.toString()
        if (id == "cv_hero2") {
            Toast.makeText(this, "HERO 2 WINS!!!!", Toast.LENGTH_LONG).show()

        }
    }

    fun getHeroInfo(view: View) {
        val intent = Intent(this, HeroInfoActivity::class.java)
        startActivity(intent)
    }
}