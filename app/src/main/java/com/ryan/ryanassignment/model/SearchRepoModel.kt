package com.ryan.ryanassignment.model

import com.google.gson.annotations.SerializedName

data class SearchRepoModel(
    @SerializedName("full_name")
    val repoName : String,
    @SerializedName("description")
    val repoDescription : String,
    @SerializedName("stargazers_count")
    val starCount: Int
)