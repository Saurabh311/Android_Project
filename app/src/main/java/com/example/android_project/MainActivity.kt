package com.example.android_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso
import java.io.InputStream
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //temp code start fredrik
        val image_url = "https://lumiere-a.akamaihd.net/v1/images/p_avengersendgame_19751_e14a0104.jpeg?region=0,0,540,810&width=480"
        val imageView = findViewById<ImageView>(R.id.characterPic)
        Picasso.get().load(image_url).into(imageView)

        //temp code Oskar
        startActivity(Intent(this, HeroInfoActivity::class.java))


    }
}