package com.ryan.ryanassignment.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ryan.ryanassignment.R

class SearchViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    var tvTitle : TextView
    var tvDescription : TextView
    var tvStar : TextView

    init {
        tvTitle = view.findViewById(R.id.tvTitle)
        tvDescription = view.findViewById(R.id.tvDescription)
        tvStar = view.findViewById(R.id.tvStar)
    }
}