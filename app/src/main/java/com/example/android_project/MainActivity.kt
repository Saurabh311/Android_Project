package com.example.android_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import java.io.InputStream
import java.net.URL

lateinit var btn_search: Button
lateinit var btn_random: Button
lateinit var btn_fight: Button
lateinit var imgBtn_charInfo: ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //temp code start fredrik
        val image_url =
            "https://lumiere-a.akamaihd.net/v1/images/p_avengersendgame_19751_e14a0104.jpeg?region=0,0,540,810&width=480"
        val imageView = findViewById<ImageView>(R.id.characterPic)
        Picasso.get().load(image_url).into(imageView)

        //temp code Oskar
        //startActivity(Intent(this, HeroInfoActivity::class.java))

        initButtons()

    }

    private fun initButtons() {
        btn_search = findViewById(R.id.buttonSearchChar)
        btn_fight = findViewById(R.id.buttonFight)
        btn_random = findViewById(R.id.buttonRandomChar)
        imgBtn_charInfo = findViewById(R.id.characterPic)

        btn_fight.setOnClickListener { startFight() }
        btn_random.setOnClickListener { getRandomChar() }
        btn_search.setOnClickListener { browseChars() }
        imgBtn_charInfo.setOnClickListener { getHeroInfo() }
    }

    private fun getHeroInfo() {
        val intent = Intent(this, HeroInfoActivity::class.java).apply {
            // putExtra(character)
        }
        startActivity(intent)

    }

    private fun browseChars() {
        val intent = Intent(this, BrowseActivity::class.java)
        startActivity(intent)
    }

    private fun getRandomChar() {
        // get new random char from API (or from hash?)

        // TEMPORARY :)
        Toast.makeText(this, "NOW YOU HAVE A NEW RANDOM CHARACTER", Toast.LENGTH_LONG).show()
    }

    private fun startFight() {
        val intent = Intent(this, GameActivity::class.java).apply {
          //  putExtra(character) // send selected character
        }
        startActivity(intent)
    }
}