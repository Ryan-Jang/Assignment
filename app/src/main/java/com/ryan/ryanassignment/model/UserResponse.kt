package com.ryan.ryanassignment.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("login")
    val userId: String,
    @SerializedName("avatar_url")
    val userProfile: String
)