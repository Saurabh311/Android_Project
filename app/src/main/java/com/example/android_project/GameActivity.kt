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
import com.example.android_project.database.CharScoreDatabaseHelper
import com.example.android_project.model.Character
import com.squareup.picasso.Picasso

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

lateinit var charsScoreDatabase : CharScoreDatabaseHelper

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val intent = getIntent()
        charsScoreDatabase = CharScoreDatabaseHelper(this)
        getScores(activeChar1)
        //getScores(activeChar2) needs to be fixed later
        initViews()

    }

    fun initViews () {
        tv_heroName1 = findViewById(R.id.tv_heroName1)
        tv_heroWins1  = findViewById(R.id.tv_heroWins1)
        tv_heroLoss1 = findViewById(R.id.tv_heroLosses1)
        iv_heroImage1 = findViewById(R.id.iv_hero1)

        tv_heroName1.setText(activeChar1.name)
        activeChar1.wins?.let { tv_heroWins1.setText(it.toString()) }
        activeChar1.loss?.let { tv_heroLoss1.setText(it.toString()) }
        Picasso.get().load(activeChar1.img?.url).into(iv_heroImage1)

        tv_heroName2 = findViewById(R.id.tv_heroName2)
        iv_heroImage2 = findViewById(R.id.iv_hero2)

        tv_heroName2.setText(activeChar1.name) //needs to be changed later
        Picasso.get().load(activeChar1.img?.url).into(iv_heroImage2)


    }

    fun getHeroInfo(view: View) {
        val id = view.id.toString()
        println(id)

        if (id.equals("2131230818")){
            val intent = Intent(this, HeroInfoActivity::class.java).apply {
                putExtra("activeChar1", activeChar1)
            }
            startActivity(intent)
        }else {
            val intent = Intent(this, HeroInfoActivity::class.java).apply {
                putExtra("activeChar1", activeChar2)
            }
            startActivity(intent)
        }


    }

    fun endGame (view: View){

        var winner : Character
        val id = view.id.toString()
        tv_vs = findViewById(R.id.vs)
        cv_hero1 = findViewById(R.id.cv_hero1)
        cv_hero2 = findViewById(R.id.cv_hero2)

        if (id == "2131230866") {
            winner = activeChar2
            activeChar2.wins = activeChar1.wins?.plus(1)
            activeChar1.loss = activeChar2.loss?.plus(1)
            cv_hero2.setCardBackgroundColor(Color.YELLOW)
            addScore()

        }else{
            winner = activeChar1
            activeChar1.wins = activeChar1.wins?.plus(1)
           // activeChar2.loss = activeChar2.loss?.plus(1)
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
        //charsScoreDatabase.addCharScore(activeChar2)
        initViews() // remove later ?
    }

    fun getScores (character: Character){ // gets value from database if exists and adds it to character
        charsScoreDatabase.getCharScore(character)
    }
}