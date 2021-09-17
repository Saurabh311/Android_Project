package com.example.android_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android_project.R

class HeroInfoActivity: AppCompatActivity() {
    lateinit var tv_heroName: TextView
    lateinit var tv_heroScore: TextView
    lateinit var iv_heroImage: ImageView
    lateinit var tv_heroDescription: TextView
    lateinit var btn_backButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heroinfo)

        tv_heroName = findViewById(R.id.hi_heroName)
        tv_heroScore = findViewById(R.id.hi_score)
        iv_heroImage = findViewById(R.id.hi_heroImage)
        tv_heroDescription = findViewById(R.id.hi_heroDescription)
        btn_backButton = findViewById(R.id.hi_btn_back)

        // temp codes
        tv_heroName.setText("Iron Man")
        iv_heroImage.setImageResource(R.drawable.ironman)
        tv_heroScore.setText(" Score: 20")


        btn_backButton.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java).apply {
                // putExtra(selectedHero)
            }

            startActivity(intent)
        }






    }
}