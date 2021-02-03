package com.sha.iproperty.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sha.iproperty.R
import com.sha.iproperty.data.model.NewsLifeCycle
import com.squareup.picasso.Picasso

class NewsLifeStyleItemsAdapter : RecyclerView.Adapter<NewsLifeStyleItemsAdapter.NewsLifeCycleViewHolder>() {

    private val items = mutableListOf<NewsLifeCycle>()

    fun setNewsLifeCycleItems(movies: List<NewsLifeCycle>) {
        this.items.clear()
        this.items.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsLifeCycleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_news, parent, false)
        return NewsLifeCycleViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsLifeCycleViewHolder, position: Int) {
        val newsLifeCycle = items[position]
        holder.descriptionTextView.text = newsLifeCycle.description
        Picasso.get().load(newsLifeCycle.thumbnailUrl).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class NewsLifeCycleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image)
        val descriptionTextView: TextView = itemView.findViewById(R.id.description)
    }
}