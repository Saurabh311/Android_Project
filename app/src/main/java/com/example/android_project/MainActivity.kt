package com.example.android_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.android_project.model.CharacterResponse
import com.example.android_project.network.APIclient
import retrofit2.Call
import retrofit2.Response
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.android_project.Model.Character
import com.squareup.picasso.Picasso
import java.lang.Exception
import kotlin.random.Random

lateinit var btn_search: Button
lateinit var btn_random: Button
lateinit var btn_fight: Button
lateinit var imgBtn_charInfo: ImageButton
var activeChar = false
const val MAX_NUM_CHARSID = 731 // max num om charids in api

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //https://www.tutorialspoint.com/how-to-implement-android-pull-to-refresh
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //temp code start fredrik
        val image_url =
            "https://lumiere-a.akamaihd.net/v1/images/p_avengersendgame_19751_e14a0104.jpeg?region=0,0,540,810&width=480"
        val imageView = findViewById<ImageView>(R.id.characterPic)
        Picasso.get().load(image_url).into(imageView)
        if (!activeChar){
            getRandomChar()
        }

        //temp code Oskar
        //startActivity(Intent(this, HeroInfoActivity::class.java))
        refreshChar()
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
            //suggest to implement the active hero variable in the top
        }
        startActivity(intent)

    }

    private fun browseChars() {
        val intent = Intent(this, BrowseActivity::class.java)
        startActivity(intent)
    }

    private fun getRandomChar() {

        val randomCharidInRange = Random.nextInt(1, MAX_NUM_CHARSID)
        //!!activechar = method that returns a api response character from a random heroid ask kristoffer
        val image_url =
            "https://lumiere-a.akamaihd.net/v1/images/p_avengersendgame_19751_e14a0104.jpeg?region=0,0,540,810&width=480"
        val imageView = findViewById<ImageView>(R.id.characterPic)
        Picasso.get().load(image_url).into(imageView)  //!!!the url will be changed to character url later from activechar

    }

    private fun refreshChar(){
        var refresh = findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        refresh.setOnRefreshListener{
            getRandomChar()
            refresh.isRefreshing = false
        }

    }

    private fun startFight() {
        val intent = Intent(this, GameActivity::class.java).apply {
          //  putExtra(character) // send selected character wich is the active hero
        }
        startActivity(intent)
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