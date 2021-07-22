package com.ryan.ryanassignment.model

import retrofit2.Callback

class SearchAgent : HttpAgent() {
    fun getUser(userName: String, callback: Callback<UserResponse>) {
        getRequestServiceInstance().searchUser(userName).enqueue(callback)
    }
    fun getSearch(userName : String, callback : Callback<ArrayList<SearchRepoModel>>) {
        getRequestServiceInstance().searchRepo(userName).enqueue(callback)
    }
}