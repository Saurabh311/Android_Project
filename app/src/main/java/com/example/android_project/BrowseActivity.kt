package com.example.android_project

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_project.adapter.RecyclerAdapter
import com.example.android_project.viewmodel.AppViewModel

class BrowseActivity : AppCompatActivity() {
    private lateinit var searchHero: EditText
    private lateinit var viewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        searchHero = findViewById(R.id.et_heroSearch)
        viewModel = ViewModelProvider(this).get(AppViewModel::class.java)

        viewModel.getCharacterList().observe(this, {
            val adapter = it.results?.let { result -> RecyclerAdapter(result, this@BrowseActivity) }
            val recyclerView: RecyclerView = findViewById(R.id.rv_heroList)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@BrowseActivity)
        })
    }

    fun searchForHero(view: View) {
        val query = searchHero.text.toString()

        //Hides the keyboard when user clicks search btn
        val currentView = this.currentFocus
        searchHero.clearFocus()
        if (currentView != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentView.windowToken, 0)
        }

        if (query != "") {
            searchHero.text.clear()
            viewModel.searchByName(query, this)

        } else {
            Toast.makeText(
                this,
                "Enter at least one character to search for heroes",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
