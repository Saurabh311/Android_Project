package com.example.android_project.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_project.MainActivity
import com.example.android_project.R
import com.example.android_project.data.Character
import com.squareup.picasso.Picasso

class RecyclerAdapter(private var heroList: List<Character>, var context: Context) :
    RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    inner class RecyclerViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bindData(character: Character) {
            val ivAvatar: ImageView = itemView.findViewById(R.id.iv_avatar)
            val tvRecyclerName: TextView = itemView.findViewById(R.id.tv_recyclerName)
            val container: CardView = itemView.findViewById(R.id.cv_heroListCard)
            container.setOnClickListener {
                val intent = Intent(context, MainActivity::class.java).apply {
                    putExtra("activeChar", character)
                }
                context.startActivity(intent)

            }
            tvRecyclerName.text = character.name
            Picasso.get().load(character.img?.url)
                .error(R.drawable.hero_placeholder_foreground)
                .into(ivAvatar)

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