package com.example.android_project

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.EventLogTags
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import com.example.android_project.model.Character
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
    lateinit var btn_backButton: Button
    lateinit var activeChar : Character
    lateinit var barEntryArrayList: ArrayList<BarEntry>
    lateinit var barDataSet: BarDataSet
    lateinit var barChart: BarChart



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
        btn_backButton = findViewById(R.id.hi_btn_back)
        barChart = findViewById(R.id.barChart)

        tv_heroName.setText(activeChar.name)
        Picasso.get().load(activeChar.img?.url).into(iv_heroImage)
        tv_heroScore.setText(" Score: ${activeChar.wins}")
        tv_fullName.setText(" Full Name: ${activeChar.bio?.fullName}")
        tv_gender.setText(" Gender: ${activeChar.appearance?.gender}")
        tv_work.setText(" Work: ${activeChar.work?.occupation}")

        var intelligence :Float = (activeChar.powerStats?.intelligence)!!.toFloat()
        var strength : Float = (activeChar.powerStats?.strength)!!.toFloat()
        var power : Float = (activeChar.powerStats?.power)!!.toFloat()
        var combat : Float = (activeChar.powerStats?.combat)!!.toFloat()
        var durability : Float = (activeChar.powerStats?.durability)!!.toFloat()
        var speed : Float = (activeChar.powerStats?.speed)!!.toFloat()

        val xValues = ArrayList<String>()
        xValues.add("Intelligence")
        xValues.add("Strength")
        xValues.add("Power")
        xValues.add("Combat")
        xValues.add("Speed")
        //xValues.add("Durability")


        barEntryArrayList = ArrayList()
        barEntryArrayList.add(BarEntry(intelligence,0, "Intelligence"))
        barEntryArrayList.add(BarEntry(strength,1, "Strength"))
        barEntryArrayList.add(BarEntry(power,2, "Power"))
        barEntryArrayList.add(BarEntry(combat, 3, "Combat"))
        barEntryArrayList.add(BarEntry(speed, 4, "Speed"))
        //barEntryArrayList.add(BarEntry(durability, 5, "Durability"))


        println(activeChar.powerStats)

        barDataSet= BarDataSet(barEntryArrayList, "Power Stats")
        val barData= BarData(xValues, barDataSet)
        barChart.data = barData
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS)
        barDataSet.valueTextColor = Color.BLACK
        barChart.setDescription("Power Statistics")
        barDataSet.valueTextSize = 15f
        barChart.animateXY(6000, 6000)

        btn_backButton.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java).apply {
                // putExtra(selectedHero)
            }
            startActivity(intent)
        }

    }
}