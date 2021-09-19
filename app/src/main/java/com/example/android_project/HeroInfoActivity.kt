package com.example.android_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android_project.R
import com.example.android_project.model.Character
import com.squareup.picasso.Picasso

class HeroInfoActivity: AppCompatActivity() {
    lateinit var tv_heroName: TextView
    lateinit var tv_heroScore: TextView
    lateinit var iv_heroImage: ImageView
    lateinit var tv_heroDescription: TextView
    lateinit var btn_backButton: Button
    lateinit var activeChar : Character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heroinfo)

        val intent = getIntent()
        activeChar = (intent.getSerializableExtra("activeChar") as Character?)!!

        tv_heroName = findViewById(R.id.hi_heroName)
        tv_heroScore = findViewById(R.id.hi_score)
        iv_heroImage = findViewById(R.id.hi_heroImage)
        tv_heroDescription = findViewById(R.id.hi_heroDescription)
        btn_backButton = findViewById(R.id.hi_btn_back)

        // temp codes

        tv_heroName.setText(activeChar.name)

        Picasso.get().load(activeChar.img?.url).into(iv_heroImage)
        tv_heroScore.setText(" Score: 20")


        btn_backButton.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java).apply {
                // putExtra(selectedHero)
            }

            startActivity(intent)
        }






    }
}