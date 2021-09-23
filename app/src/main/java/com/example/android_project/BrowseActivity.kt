package com.example.android_project

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_project.adapter.RecyclerAdapter
import com.example.android_project.network.APIhelper

class BrowseActivity : AppCompatActivity() {
    lateinit var et_searchHero: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)

        et_searchHero = findViewById(R.id.et_heroSearch)
    }

    fun searchForHero(view: View) {
        val query = et_searchHero.text.toString()

        //Hides the keyboard when user clicks search btn
        val view = this.currentFocus
        et_searchHero.clearFocus()
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        if (query != "") {
            et_searchHero.text.clear()
            getHeroes(query)

        } else {
            Toast.makeText(
                this,
                "Enter at least one character to search for heroes",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun getHeroes(query: String) {
        val apiHelper = APIhelper()
        apiHelper.getCharByName(query, this@BrowseActivity).observe(this, Observer {
            val adapter = it.results?.let { result -> RecyclerAdapter(result, this@BrowseActivity) }
            val recyclerView: RecyclerView = findViewById(R.id.rv_heroList)
            recyclerView.adapter = adapter
            recyclerView?.layoutManager = LinearLayoutManager(this@BrowseActivity)
        })
    }
}
