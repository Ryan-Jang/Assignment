package com.ryan.ryanassignment.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ryan.ryanassignment.R
import com.ryan.ryanassignment.model.SearchRepoModel
import com.ryan.ryanassignment.model.UserResponse

class SearchAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var itemList : ArrayList<Any>? = null

    fun setList(list : ArrayList<Any>) {
        itemList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView : View
        return if (viewType == 0) {
            itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_user, null)
            UserViewHolder(itemView)
        } else {
            itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_search, null)
            SearchViewHolder(itemView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == 0) {
            val model: UserResponse = itemList?.get(position) as UserResponse
            val userViewHolder: UserViewHolder = holder as UserViewHolder

            Glide.with(userViewHolder.itemView).load(model.userProfile).diskCacheStrategy(DiskCacheStrategy.ALL).into(userViewHolder.ivProfile)
            userViewHolder.tvName.text = model.userId
        } else {
            val model: SearchRepoModel = itemList?.get(position) as SearchRepoModel
            val searchViewHolder: SearchViewHolder = holder as SearchViewHolder

            searchViewHolder.tvTitle.text = model.repoName
            searchViewHolder.tvDescription.text = model.repoDescription
            searchViewHolder.tvStar.text = model.starCount.toString()
        }
    }

    override fun getItemCount(): Int {
        return if (itemList == null) 0 else itemList!!.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0)
            0
        else
            1
    }
}