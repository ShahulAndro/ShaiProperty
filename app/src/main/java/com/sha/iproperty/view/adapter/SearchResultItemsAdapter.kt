package com.sha.iproperty.view.adapter

import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sha.iproperty.R
import com.sha.iproperty.data.model.PropertyItem
import java.util.logging.Handler

class SearchResultItemsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_DATA = 0;
        private const val VIEW_TYPE_PROGRESS = 1;
        private const val LOAD_MORE = "Load More";
    }

    private val items = mutableListOf<Any>()
    var onItemClick: ((PropertyItem) -> Unit)? = null

    fun setSearchResultItems(suggestionList: List<PropertyItem>) {
        this.items.clear()
        this.items.addAll(suggestionList)
        notifyDataSetChanged()
    }

    fun addMoreSearchResultItems(suggestionList: List<PropertyItem>) {
        this.items.addAll(suggestionList)
        notifyDataSetChanged()
    }
    
    fun addLoadMore() {
        this.items.add(LOAD_MORE)
    }

    fun removeLoadMore() {
        this.items.removeAt(this.items.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType)
        {
            VIEW_TYPE_DATA -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_search_result, parent, false)
                SearchItemViewHolder(view)
            }
            VIEW_TYPE_PROGRESS -> {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.row_load_more, parent, false)
                LoadingViewHolder(view)
            }
            else -> throw IllegalArgumentException("Different View type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SearchItemViewHolder) {
            bindData(holder, position)
        } else if (holder is LoadingViewHolder) {
            showLoadingView(holder, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        var viewtype = items[position]
        return when(viewtype) {
            LOAD_MORE  -> VIEW_TYPE_PROGRESS
            else -> VIEW_TYPE_DATA
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun bindData(holder: SearchItemViewHolder, position: Int) {
        val propertyItem = items[position] as PropertyItem
        val price = propertyItem.prices!!.first()

        val adapter = PropertyMediaItemsAdapter()
        adapter.setMediaItems(propertyItem.medias!!)

        holder.propertyRecyclerView.adapter = adapter
        holder.price.text = "${price.currency} ${price.max}"
        holder.title.text = propertyItem.title
        holder.address.text = propertyItem.address?.formattedAddress
        holder.propertyType.text = propertyItem.propertyType
        holder.builtUpSize.text = "${propertyItem.attributes?.builtUp} ${propertyItem.attributes?.sizeUnit}"
        holder.furnishing.text = propertyItem.attributes?.furnishing
        holder.bedRooms.text = propertyItem.attributes?.bedroom
        holder.bathRooms.text = propertyItem.attributes?.bathroom
        holder.carParking.text = propertyItem.attributes?.carPark
    }

    private fun showLoadingView(viewHolder: LoadingViewHolder, position: Int) {}


    private inner class SearchItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(items[adapterPosition] as PropertyItem)
            }
        }

        val propertyRecyclerView: RecyclerView = itemView.findViewById(R.id.propertyRecyclerView)
        val price: TextView = itemView.findViewById(R.id.price)
        val title: TextView = itemView.findViewById(R.id.propertyTitle)
        val address: TextView = itemView.findViewById(R.id.address)
        val propertyType: TextView = itemView.findViewById(R.id.propertyType)
        val builtUpSize: TextView = itemView.findViewById(R.id.builtUpSize)
        val furnishing: TextView = itemView.findViewById(R.id.furnishing)
        val bedRooms: TextView = itemView.findViewById(R.id.bedRooms)
        val carParking: TextView = itemView.findViewById(R.id.carParking)
        val bathRooms: TextView = itemView.findViewById(R.id.bathRooms)
    }

    private class LoadingViewHolder internal constructor(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
        var progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
    }
}