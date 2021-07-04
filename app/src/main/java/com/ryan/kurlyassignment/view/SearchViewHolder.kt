package com.ryan.kurlyassignment.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ryan.kurlyassignment.R

class SearchViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    var tvTitle : TextView
    var tvDescription : TextView
    var tvLanguage : TextView

    init {
        tvTitle = view.findViewById(R.id.tvTitle)
        tvDescription = view.findViewById(R.id.tvDescription)
        tvLanguage = view.findViewById(R.id.tvLanguage)
    }
}