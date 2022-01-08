package com.example.dsc_sit_21070122509.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dsc_sit_21070122509.R
import com.example.dsc_sit_21070122509.model.NewsModel

class NewsAdapter (val newsList: List<NewsModel>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_newsRequestList = itemView.findViewById<TextView>(R.id.txt_newsRequestList)
        val img_newsRequestList = itemView.findViewById<ImageView>(R.id.img_newsRequestList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_list, parent,
            false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = newsList[position]
        holder.txt_newsRequestList.text = item.content
        Glide.with(holder.img_newsRequestList.context)
            .load(item.imageUrl)
            .into(holder.img_newsRequestList)

    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}