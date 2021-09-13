package com.example.android_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

lateinit var iv_hero1: ImageView
lateinit var iv_hero2: ImageView
lateinit var tv_hero1: TextView
lateinit var tv_hero2: TextView
lateinit var btn_hero1: Button
lateinit var btn_hero2: Button

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        //temp code start Oskar
        val image_url = "https://static01.nyt.com/images/2020/09/21/multimedia/21parenting-superhero/21parenting-superhero-superJumbo.jpg?quality=90&auto=webp"
        val imageView = findViewById<ImageView>(R.id.iv_hero1)
        val imageView2 = findViewById<ImageView>(R.id.iv_hero2)
        Picasso.get().load(image_url).into(imageView)
        Picasso.get().load(image_url).into(imageView2)

    }
}