package com.example.android_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.lifecycle.Observer
import com.example.android_project.network.APIclient
import retrofit2.Call
import retrofit2.Response
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.example.android_project.model.Character
import com.example.android_project.network.APIhelper
import com.squareup.picasso.Picasso
import kotlin.random.Random

lateinit var btn_search: Button
lateinit var btn_random: Button
lateinit var btn_fight: Button
lateinit var imgBtn_charInfo: ImageButton
lateinit var activeChar1 : Character
lateinit var activeChar2 : Character
const val MAX_NUM_CHARSID = 731 // max num om charids in api
lateinit var charImageView : ImageView
lateinit var charName : TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var test = APIhelper()

        if (intent.hasExtra("activeChar")) {
            activeChar = intent.getSerializableExtra("activeChar") as Character
        }
        
        charImageView = findViewById(R.id.characterPic)
        charName = findViewById(R.id.characterName)

        if (::activeChar1.isInitialized){ // checks if we need to get a new hero
            initViews()
        }else{
            getRandomChar()
        }
        refreshChar()
        initButtons()

        //kommer alltid vara groot
        test.getCharById("70").observe(this, Observer {
            //gör vad du vill med datan här, "it" är objektet
            println(it)
            println(it.name)
            println(it.bio?.fullName)
        })
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
            putExtra("activeChar1", activeChar1)
        }
        startActivity(intent)

    }

    private fun browseChars() {
        val intent = Intent(this, BrowseActivity::class.java)
        startActivity(intent)
    }

    private fun getRandomChar() {
        var randCharId = Random.nextInt(1, MAX_NUM_CHARSID).toString()
        searchHeroById(randCharId)
    }

    fun searchHeroById(id: String) {
        val charId = id;
        val client = APIclient.apiService.fetchCharacterById(charId)
        client.enqueue(object : retrofit2.Callback<Character> {
            override fun onResponse(
                call: Call<Character>,
                response: Response<Character>
            ) {
                if (response.isSuccessful) {
                    Log.d("characters", "" + response.body())
                    setActiveChar(response.body()!!)
                }
            }
            override fun onFailure(call: Call<Character>, t: Throwable) {
                Log.e("failed", "" + t.message)
            }
        })
    }

    fun setActiveChar (char : Character){
        activeChar1 = char
        initViews()
    }

    fun initViews () {

        Picasso.get().load(activeChar1.img?.url).into(charImageView)
        charName.text = activeChar1.name
    }

    private fun refreshChar(){
        var refresh = findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        refresh.setOnRefreshListener{
            getRandomChar()
            refresh.isRefreshing = false
        }

    }

    private fun startFight() {

        if (::activeChar1.isInitialized){
            val intent = Intent(this, GameActivity::class.java).apply {
                putExtra("activeChar1", activeChar1) // send selected character wich is the active hero
               // putExtra("activeChar2", activeChar2) // send selected character wich is the active hero
            }
            startActivity(intent)
        }else{
            Toast.makeText(applicationContext,"Please select a hero first",Toast.LENGTH_SHORT).show()
        }

    }

}