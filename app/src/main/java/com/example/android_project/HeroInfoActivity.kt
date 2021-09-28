package com.example.android_project

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android_project.data.Character
import com.example.android_project.database.CharScoreDatabaseHelper
import com.github.mikephil.charting.charts. *
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.squareup.picasso.Picasso

class HeroInfoActivity: AppCompatActivity() {
    private lateinit var heroName: TextView
    private lateinit var heroScore: TextView
    private lateinit var heroImage: ImageView
    private lateinit var fullName: TextView
    private lateinit var gender: TextView
    private lateinit var work: TextView
    private lateinit var activeChar : Character
    private lateinit var barEntryArrayList: ArrayList<BarEntry>
    private lateinit var barDataSet: BarDataSet
    private lateinit var barChart: BarChart
    private lateinit var charsScoreDatabase: CharScoreDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heroinfo)

        val intent = intent
        activeChar = (intent.getSerializableExtra("activeChar") as Character?)!!

        heroName = findViewById(R.id.hi_heroName)
        heroScore = findViewById(R.id.hi_score)
        heroImage = findViewById(R.id.hi_heroImage)
        fullName = findViewById(R.id.hi_fullName)
        gender = findViewById(R.id.hi_gender)
        work = findViewById(R.id.hi_work)
        barChart = findViewById(R.id.barChart)

        heroName.text = activeChar.name
        Picasso.get().load(activeChar.img?.url)
            .error(R.drawable.hero_placeholder_foreground)
            .into(heroImage)
        fullName.text = "Full Name: ${activeChar.bio?.fullName}"
        gender.text = "Gender: ${activeChar.appearance?.gender}"
        work.text = "Work: ${activeChar.work?.occupation}"
        charsScoreDatabase = CharScoreDatabaseHelper(this)
        charsScoreDatabase.getCharScore(activeChar)
        heroScore.text = "Won: ${activeChar.wins}         Lost:${activeChar.loss}"
        getBarChart()
    }

    private fun getBarChart() {
        val powerStats : ArrayList<Float> = ArrayList()

        val intelligence : Float = if((activeChar.powerStats?.intelligence!!) != "null")
            (activeChar.powerStats?.intelligence)!!.toFloat() else 0f
        powerStats.add(intelligence)

        val strength : Float = if((activeChar.powerStats?.strength!!) != "null")
            (activeChar.powerStats?.strength)!!.toFloat() else 0f
        powerStats.add(strength)

        val power : Float = if((activeChar.powerStats?.power!!) != "null")
            (activeChar.powerStats?.power)!!.toFloat() else 0f
        powerStats.add(power)

        val combat : Float = if((activeChar.powerStats?.combat!!) != "null")
            (activeChar.powerStats?.combat)!!.toFloat() else 0f
        powerStats.add(combat)

        val speed : Float = if((activeChar.powerStats?.speed!!) != "null")
            (activeChar.powerStats?.speed)!!.toFloat() else 0f
        powerStats.add(speed)

        val xValues = ArrayList<String>()
        xValues.add("Intelligence")
        xValues.add("Strength")
        xValues.add("Power")
        xValues.add("Combat")
        xValues.add("Speed")

        barEntryArrayList = ArrayList()
        for (item in powerStats.indices){
            barEntryArrayList.add(BarEntry(powerStats[item], item))
        }

        barDataSet= BarDataSet(barEntryArrayList, "Power Statistics")
        val barData= BarData(xValues, barDataSet)
        barChart.data = barData
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS)
        barDataSet.valueTextColor = Color.BLACK
        barChart.setDescription("")
        barDataSet.valueTextSize = 15f
        barChart.animateXY(500, 1500)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.hero_info_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true    }
}