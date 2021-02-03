package com.sha.iproperty.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sha.iproperty.R

class RecentSearchItemsAdapter : RecyclerView.Adapter<RecentSearchItemsAdapter.SearchItemViewHolder>() {

    private val items = mutableListOf<String>()

    fun setRecentSearchItems(suggestionList: List<String>) {
        this.items.clear()
        this.items.addAll(suggestionList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_suggestion_item, parent, false)
        return SearchItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        val suggestion = items[position]
        holder.suggestion.text = suggestion
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class SearchItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.iv_suggestion_item_icon)
        val suggestion: TextView = itemView.findViewById(R.id.tv_suggestion_item_title)
    }
}