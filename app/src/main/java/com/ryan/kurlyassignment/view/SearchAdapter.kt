package com.ryan.kurlyassignment.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ryan.kurlyassignment.R

class SearchAdapter : RecyclerView.Adapter<SearchViewHolder>() {
    private lateinit var itemList : ArrayList

    fun setList(list : ArrayList) {
        itemList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView : View = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent)
        return SearchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return if (itemList == null) 0 else itemList.size
    }
}