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
    lateinit var tv_heroName: TextView
    lateinit var tv_heroScore: TextView
    lateinit var iv_heroImage: ImageView
    lateinit var tv_fullName: TextView
    lateinit var tv_gender: TextView
    lateinit var tv_work: TextView
    lateinit var activeChar : Character
    lateinit var barEntryArrayList: ArrayList<BarEntry>
    lateinit var barDataSet: BarDataSet
    lateinit var barChart: BarChart
    lateinit var charsScoreDatabase: CharScoreDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heroinfo)

        val intent = getIntent()
        activeChar = (intent.getSerializableExtra("activeChar") as Character?)!!

        tv_heroName = findViewById(R.id.hi_heroName)
        tv_heroScore = findViewById(R.id.hi_score)
        iv_heroImage = findViewById(R.id.hi_heroImage)
        tv_fullName = findViewById(R.id.hi_fullName)
        tv_gender = findViewById(R.id.hi_gender)
        tv_work = findViewById(R.id.hi_work)
        barChart = findViewById(R.id.barChart)

        tv_heroName.setText(activeChar.name)
        Picasso.get().load(activeChar.img?.url)
            .error(R.drawable.hero_placeholder_foreground)
            .into(iv_heroImage)
        tv_fullName.setText("Full Name: ${activeChar.bio?.fullName}")
        tv_gender.setText("Gender: ${activeChar.appearance?.gender}")
        tv_work.setText("Work: ${activeChar.work?.occupation}")
        charsScoreDatabase = CharScoreDatabaseHelper(this)
        charsScoreDatabase.getCharScore(activeChar)
        tv_heroScore.setText("Won: ${activeChar.wins}         Lost:${activeChar.loss}")
        getBarChart()
    }

    private fun getBarChart() {
        var powerStats : ArrayList<Float>
        powerStats = ArrayList()

        var intelligence : Float = if(!((activeChar.powerStats?.intelligence!!).equals("null")))
            (activeChar.powerStats?.intelligence)!!.toFloat() else 0f
        powerStats.add(intelligence)

        var strength : Float = if(!((activeChar.powerStats?.strength!!).equals("null")))
            (activeChar.powerStats?.strength)!!.toFloat() else 0f
        powerStats.add(strength)

        var power : Float = if(!((activeChar.powerStats?.power!!).equals("null")))
            (activeChar.powerStats?.power)!!.toFloat() else 0f
        powerStats.add(power)

        var combat : Float = if(!((activeChar.powerStats?.combat!!).equals("null")))
            (activeChar.powerStats?.combat)!!.toFloat() else 0f
        powerStats.add(combat)

        var speed : Float = if(!((activeChar.powerStats?.speed!!).equals("null")))
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