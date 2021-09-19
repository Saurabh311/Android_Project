package com.example.android_project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_project.R
import com.example.android_project.model.Character
import com.squareup.picasso.Picasso

class RecyclerAdapter(var heroList: List<Character>) :
    RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {
    //Add the data source as parameter to constructor after context.

    inner class RecyclerViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bindData(character: Character) {
            val iv_avatar: ImageView = itemView.findViewById(R.id.iv_avatar)
            val tv_recyclerName: TextView = itemView.findViewById(R.id.tv_recyclerName)

            tv_recyclerName.text = character.name
            Picasso.get().load(character.img?.url).into(iv_avatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindData(heroList[position])

    }

    override fun getItemCount(): Int {
        return heroList.size
    }


}