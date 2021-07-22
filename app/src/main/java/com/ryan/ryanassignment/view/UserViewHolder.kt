package com.ryan.ryanassignment.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ryan.ryanassignment.R

class UserViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    var ivProfile: ImageView
    var tvName: TextView

    init {
        ivProfile = view.findViewById(R.id.ivProfile)
        tvName = view.findViewById(R.id.tvName)
    }
}