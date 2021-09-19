package com.example.android_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.android_project.network.APIclient
import retrofit2.Call
import retrofit2.Response
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.example.android_project.model.Character
import com.squareup.picasso.Picasso
import kotlin.random.Random

lateinit var btn_search: Button
lateinit var btn_random: Button
lateinit var btn_fight: Button
lateinit var imgBtn_charInfo: ImageButton
lateinit var activeChar : Character
const val MAX_NUM_CHARSID = 731 // max num om charids in api
lateinit var charImageView : ImageView
lateinit var charName : TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        charImageView = findViewById(R.id.characterPic)
        charName = findViewById(R.id.characterName)

        if (::activeChar.isInitialized){ // checks if we need to get a new hero
            initViews()
        }else{
            getRandomChar()
        }
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
            putExtra("activeChar", activeChar)
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
        activeChar = char
        initViews()
    }

    fun initViews () {

        Picasso.get().load(activeChar.img?.url).into(charImageView)
        charName.text = activeChar.name
    }

    private fun refreshChar(){
        var refresh = findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        refresh.setOnRefreshListener{
            getRandomChar()
            refresh.isRefreshing = false
        }

    }

    private fun startFight() {

        if (::activeChar.isInitialized){
            val intent = Intent(this, GameActivity::class.java).apply {
                putExtra("activeChar", activeChar) // send selected character wich is the active hero
            }
            startActivity(intent)
        }else{
            Toast.makeText(applicationContext,"Please select a hero first",Toast.LENGTH_SHORT).show()
        }

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