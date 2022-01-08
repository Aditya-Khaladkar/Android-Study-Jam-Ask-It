package com.example.dsc_sit_21070122509.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dsc_sit_21070122509.R

class IntroAdapter (var introList: List<String>) : RecyclerView.Adapter<IntroAdapter.IntroViewHolder>() {
    class IntroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val introTaskName = itemView.findViewById<TextView>(R.id.introTaskName)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.intro_task_list, parent,
            false)
        return IntroViewHolder(view)
    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {
        val item = introList[position]
        holder.introTaskName.text = item
    }

    override fun getItemCount(): Int {
        return introList.size
    }
}