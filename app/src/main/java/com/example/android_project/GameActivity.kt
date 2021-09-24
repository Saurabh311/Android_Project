package com.example.android_project

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import com.example.android_project.database.CharScoreDatabaseHelper
import com.example.android_project.model.Character
import com.example.android_project.viewmodel.MainViewModel
import com.squareup.picasso.Picasso



lateinit var activeChar1 : Character
lateinit var activeChar2 : Character

lateinit var charsScoreDatabase : CharScoreDatabaseHelper
lateinit var viewModel: MainViewModel

class GameActivity : AppCompatActivity() {
    lateinit var tv_heroName1: TextView
    lateinit var tv_heroName2: TextView
    lateinit var tv_heroWins1:TextView
    lateinit var tv_heroWins2:TextView
    lateinit var tv_heroLoss1:TextView
    lateinit var tv_heroLoss2:TextView
    lateinit var tv_vs: TextView
    lateinit var btn_heroInfo1: Button
    lateinit var btn_heroInfo2: Button
    lateinit var iv_heroImage1: ImageView
    lateinit var iv_heroImage2: ImageView
    lateinit var cv_hero1: CardView
    lateinit var cv_hero2: CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        charsScoreDatabase = CharScoreDatabaseHelper(this)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val intent = getIntent()
        activeChar1 = intent.getSerializableExtra("activeChar") as Character

        observeCharacterChange()
        viewModel.randomCharacter()

    }

    private fun observeCharacterChange() {
        viewModel.getCharacter().observe(this, {
            activeChar2 = it
            getScores(activeChar1)
            getScores(activeChar2)
            initViews()

        })
    }

    fun initViews () {
        tv_heroName1 = findViewById(R.id.tv_heroName1)
        tv_heroWins1  = findViewById(R.id.tv_heroWins1)
        tv_heroLoss1 = findViewById(R.id.tv_heroLosses1)
        iv_heroImage1 = findViewById(R.id.iv_hero1)

        tv_heroName1.text = activeChar1.name
        tv_heroWins1.text  = activeChar1.wins.toString()
        tv_heroLoss1.text = activeChar1.loss.toString()
        Picasso.get().load(activeChar1.img?.url).into(iv_heroImage1)

        tv_heroName2 = findViewById(R.id.tv_heroName2)
        iv_heroImage2 = findViewById(R.id.iv_hero2)
        tv_heroWins2 = findViewById(R.id.tv_heroWins2)
        tv_heroLoss2 = findViewById(R.id.tv_heroLosses2)

        tv_heroName2.text = activeChar2.name
        tv_heroWins2.text = activeChar2.wins.toString()
        tv_heroLoss2.text = activeChar2.loss.toString()
        Picasso.get().load(activeChar2.img?.url).into(iv_heroImage2)
    }

    fun getHeroInfo(view: View) {
        val id = view.toString()
        println(id)

        if (id.contains("btn_heroInfo1")){
            val intent = Intent(this, HeroInfoActivity::class.java).apply {
                putExtra("activeChar", activeChar1)
            }
            startActivity(intent)
        }else {
            val intent = Intent(this, HeroInfoActivity::class.java).apply {
                putExtra("activeChar", activeChar2)
            }
            startActivity(intent)
        }


    }

    fun endGame (view: View){

        var winner : Character
        val id = view.toString()
        tv_vs = findViewById(R.id.vs)
        cv_hero1 = findViewById(R.id.cv_hero1)
        cv_hero2 = findViewById(R.id.cv_hero2)

        if (id.contains("hero2")) {
            activeChar2.wins = activeChar2.wins?.plus(1)
            activeChar1.loss = activeChar1.loss?.plus(1)
            cv_hero2.setCardBackgroundColor(Color.YELLOW)
            addScore()

        }else{
            //println(activeChar1.wins)
            activeChar1.wins = activeChar1.wins?.plus(1)
            activeChar2.loss = activeChar2.loss?.plus(1)
            cv_hero1.setCardBackgroundColor(Color.YELLOW)
            addScore()
        }
        tv_vs.text = "Winner"


        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java).apply {

            }
            startActivity(intent)
        }, 2000)

    }
    fun addScore () {
        charsScoreDatabase.addCharScore(activeChar1)
        charsScoreDatabase.addCharScore(activeChar2)
        initViews()
    }

    fun getScores (character: Character){ // gets value from database if exists and adds it to character
        charsScoreDatabase.getCharScore(character)
    }
}