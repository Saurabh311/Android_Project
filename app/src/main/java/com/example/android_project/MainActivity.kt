package com.example.android_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.example.android_project.data.Character
import com.example.android_project.viewmodel.AppViewModel
import com.squareup.picasso.Picasso

lateinit var activeChar: Character
const val MAX_NUM_CHARSID = 731 // max num om charids in api

class MainActivity : AppCompatActivity() {

    private lateinit var btnSearch: Button
    private lateinit var btnRandom: Button
    private lateinit var btnFight: Button
    private lateinit var imgBtnCharInfo: ImageButton
    private lateinit var charImageView: ImageView
    private lateinit var charName: TextView

    private lateinit var viewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(AppViewModel::class.java)

        charImageView = findViewById(R.id.characterPic)
        charName = findViewById(R.id.characterName)

        if (intent.hasExtra("activeChar")) {
            activeChar = intent.getSerializableExtra("activeChar") as Character
        }
        if (::activeChar.isInitialized) { // if activeChar exists change the viewModel
            viewModel.setCharacter(activeChar)
        } else {
            viewModel.randomCharacter()
        }

        //places an observer to update the UI every time the character in viewModel changes
        viewModel.getCharacter().observe(this, { character ->
            charName.text = character.name
            charName.visibility = View.VISIBLE
            Picasso.get().load(character.img?.url)
                .error(R.drawable.hero_placeholder_foreground)
                .into(charImageView)

            activeChar = character
        })

        initButtons()
        refreshChar()
    }

    private fun initButtons() {

        btnSearch = findViewById(R.id.buttonSearchChar)
        btnFight = findViewById(R.id.buttonFight)
        btnRandom = findViewById(R.id.buttonRandomChar)
        imgBtnCharInfo = findViewById(R.id.characterPic)

        btnFight.setOnClickListener { startFight() }
        btnRandom.setOnClickListener {
            viewModel.randomCharacter()
        }
        btnSearch.setOnClickListener { browseChars() }
        imgBtnCharInfo.setOnClickListener { getHeroInfo() }
    }

    private fun refreshChar() {
        val refresh = findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        refresh.setOnRefreshListener {
            viewModel.randomCharacter()
            refresh.isRefreshing = false
        }
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

    private fun startFight() {

        if (::activeChar.isInitialized) {
            val intent = Intent(this, GameActivity::class.java).apply {
                putExtra(
                    "activeChar",
                    activeChar
                )
            }
            startActivity(intent)
        } else {
            Toast.makeText(applicationContext, "Please select a hero first", Toast.LENGTH_SHORT)
                .show()
        }
    }
}