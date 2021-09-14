package com.example.android_project.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_project.R

class RecyclerAdapter(private val context: Context): RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>(){
    //Add the data source as parameter to constructor after context.

    inner class RecyclerViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.itemView.apply {
            val iv_avatar: ImageView = findViewById(R.id.iv_avatar) //We might need to change this depending on the api response?
            //iv_avatar =
            val tv_recyclerName: TextView = findViewById(R.id.tv_recyclerName)
            //tv_recyclerName =
        }
        TODO("Not yet implemented, need data to bind to the recycler_layout for the view")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented, return the size of the data source")
    }


}