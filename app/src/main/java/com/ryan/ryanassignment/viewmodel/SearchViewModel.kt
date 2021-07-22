package com.ryan.ryanassignment.viewmodel

import com.ryan.ryanassignment.model.SearchAgent
import com.ryan.ryanassignment.model.SearchRepoModel
import com.ryan.ryanassignment.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel {
    companion object {
        private var searchAgent = SearchAgent()
        private var observerList : ArrayList<Observer> = ArrayList()

        fun addObserver(observer : Observer) {
            observerList.add(observer)
        }

        fun removeObserver(observer : Observer) {
            if (observerList.size != 0)
                observerList.removeAt(observerList.indexOf(observer))
        }

        fun getUser(query: String) {
            searchAgent.getUser(query, object : Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    if (response.isSuccessful) {
                        val list: ArrayList<Any> = ArrayList()
                        list.add(response.body()!!)
                        getRepoList(query, list)
                    } else
                        notifyResult(null)
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    notifyResult(null)
                }
            })
        }

        fun getRepoList(query : String, list: ArrayList<Any>) {
            searchAgent.getSearch(query, object : Callback<ArrayList<SearchRepoModel>> {
                override fun onResponse(call: Call<ArrayList<SearchRepoModel>>, response: Response<ArrayList<SearchRepoModel>>) {
                    if (response.isSuccessful) {
                        list.addAll(response.body()!!)
                        notifyResult(list)
                    } else
                        notifyResult(null)
                }

                override fun onFailure(call: Call<ArrayList<SearchRepoModel>>, t: Throwable) {
                    notifyResult(null)
                }
            })
        }

        private fun notifyResult(obj : Any?) {
            observerList.forEach {
                it.notifyUpdate(obj)
            }
        }
    }

    interface Observer {
        fun notifyUpdate(obj: Any?)
    }
}