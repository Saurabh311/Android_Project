package com.example.android_project

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import com.example.android_project.database.CharScoreDatabaseHelper
import com.example.android_project.data.Character
import com.example.android_project.viewmodel.AppViewModel
import com.squareup.picasso.Picasso

lateinit var activeChar1: Character
lateinit var activeChar2: Character
lateinit var charsScoreDatabase: CharScoreDatabaseHelper
lateinit var viewModel: AppViewModel

class GameActivity : AppCompatActivity() {
    private lateinit var heroName1: TextView
    private lateinit var heroName2: TextView
    private lateinit var heroWins1: TextView
    private lateinit var heroWins2: TextView
    private lateinit var heroLoss1: TextView
    private lateinit var heroLoss2: TextView
    private lateinit var vs: TextView
    private lateinit var heroImage1: ImageView
    private lateinit var heroImage2: ImageView
    private lateinit var hero1: CardView
    private lateinit var hero2: CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        charsScoreDatabase = CharScoreDatabaseHelper(this)
        viewModel = ViewModelProvider(this).get(AppViewModel::class.java)
        activeChar1 = intent.getSerializableExtra("activeChar") as Character

        viewModel.randomCharacter()

        viewModel.getCharacter().observe(this, {
            activeChar2 = it
            getScores(activeChar1)
            getScores(activeChar2)
            initViews()
        })
    }

    private fun initViews() {
        heroName1 = findViewById(R.id.tv_heroName1)
        heroWins1 = findViewById(R.id.tv_heroWins1)
        heroLoss1 = findViewById(R.id.tv_heroLosses1)
        heroImage1 = findViewById(R.id.iv_hero1)

        heroName1.text = activeChar1.name
        heroWins1.text = activeChar1.wins.toString()
        heroLoss1.text = activeChar1.loss.toString()
        Picasso.get().load(activeChar1.img?.url)
            .error(R.drawable.hero_placeholder_foreground)
            .into(heroImage1)

        heroName2 = findViewById(R.id.tv_heroName2)
        heroImage2 = findViewById(R.id.iv_hero2)
        heroWins2 = findViewById(R.id.tv_heroWins2)
        heroLoss2 = findViewById(R.id.tv_heroLosses2)

        heroName2.text = activeChar2.name
        heroWins2.text = activeChar2.wins.toString()
        heroLoss2.text = activeChar2.loss.toString()
        Picasso.get().load(activeChar2.img?.url)
            .error(R.drawable.hero_placeholder_foreground)
            .into(heroImage2)
    }

    fun getHeroInfo(view: View) {
        val id = view.toString()
        println(id)

        if (id.contains("btn_heroInfo1")) {
            val intent = Intent(this, HeroInfoActivity::class.java).apply {
                putExtra("activeChar", activeChar1)
            }
            startActivity(intent)
        } else {
            val intent = Intent(this, HeroInfoActivity::class.java).apply {
                putExtra("activeChar", activeChar2)
            }
            startActivity(intent)
        }
    }

    fun endRound(view: View) {

        val id = view.toString()
        vs = findViewById(R.id.vs)
        hero1 = findViewById(R.id.cv_hero1)
        hero2 = findViewById(R.id.cv_hero2)

        if (id.contains("hero2")) {
            activeChar2.wins = activeChar2.wins?.plus(1)
            activeChar1.loss = activeChar1.loss?.plus(1)
            hero2.setCardBackgroundColor(Color.YELLOW)
            addScore()

        } else {
            activeChar1.wins = activeChar1.wins?.plus(1)
            activeChar2.loss = activeChar2.loss?.plus(1)
            hero1.setCardBackgroundColor(Color.YELLOW)
            addScore()
        }
        vs.text = "WINNER"

        Handler().postDelayed({
            viewModel.randomCharacter()
            hero1.setCardBackgroundColor(Color.WHITE)
            hero2.setCardBackgroundColor(Color.WHITE)
            vs.text = "VS"
        }, 1500)
    }

    private fun addScore() {
        charsScoreDatabase.addCharScore(activeChar1)
        charsScoreDatabase.addCharScore(activeChar2)
        initViews()
    }

    private fun getScores(character: Character) { // gets value from database if exists and adds it to character
        charsScoreDatabase.getCharScore(character)
    }
}