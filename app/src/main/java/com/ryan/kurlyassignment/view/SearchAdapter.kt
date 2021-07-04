package com.ryan.kurlyassignment.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ryan.kurlyassignment.R
import com.ryan.kurlyassignment.model.SearchModel

class SearchAdapter : RecyclerView.Adapter<SearchViewHolder>() {
    private var itemList : ArrayList<SearchModel>? = null

    fun setList(list : ArrayList<SearchModel>) {
        itemList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView : View = LayoutInflater.from(parent.context).inflate(R.layout.item_search, null)
        return SearchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.tvTitle.text = itemList?.get(position)?.repoName
        holder.tvDescription.text = itemList?.get(position)?.repoDescription
        holder.tvLanguage.text = itemList?.get(position)?.codeLanguage
    }

    override fun getItemCount(): Int {
        return if (itemList == null) 0 else itemList!!.size
    }
}