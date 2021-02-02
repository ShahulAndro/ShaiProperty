package com.sha.iproperty.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.sha.iproperty.R
import com.sha.iproperty.data.model.Media
import com.squareup.picasso.Picasso

class PropertyMediaItemsAdapter : RecyclerView.Adapter<PropertyMediaItemsAdapter.MediaItemViewHolder>() {

    private val items = mutableListOf<Media>()

    fun setMediaItems(list: List<Media>) {
        this.items.clear()
        this.items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_media, parent, false)
        return MediaItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MediaItemViewHolder, position: Int) {
        val media = items[position]
        Picasso.get().load(media.thumbnailUrl).placeholder(R.drawable.ic_images_placeholder).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MediaItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.mediaImage)
    }
}