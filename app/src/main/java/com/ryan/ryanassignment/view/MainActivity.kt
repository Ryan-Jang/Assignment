package com.ryan.ryanassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ryan.ryanassignment.R
import com.ryan.ryanassignment.viewmodel.SearchViewModel

class MainActivity : AppCompatActivity(), SearchViewModel.Observer {
    private lateinit var rvSearch: RecyclerView
    private lateinit var tvNoResult: TextView
    private lateinit var pbLoading: ProgressBar

    private lateinit var searchAdapter: SearchAdapter

    private lateinit var userName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvSearch = findViewById(R.id.rvSearch)
        tvNoResult = findViewById(R.id.tvNoResult)
        pbLoading = findViewById(R.id.pbLoading)


        if (intent?.dataString == null)
            userName = ""

        intent?.dataString?.let {
            userName = it
        }

        SearchViewModel.addObserver(this)

        initList()
    }

    override fun onDestroy() {
        super.onDestroy()
        SearchViewModel.removeObserver(this)
    }

    private fun initList() {
        searchAdapter = SearchAdapter()
        rvSearch.adapter = searchAdapter
        rvSearch.layoutManager = LinearLayoutManager(this)
        rvSearch.addItemDecoration(
            DividerItemDecoration(this, LinearLayout.VERTICAL).apply {
                getDrawable(R.drawable.shape_divider)?.let {
                    setDrawable(it)
                }
        })

        requestRepoList()
    }

    private fun requestRepoList() {
        if (userName.trim() == "")
            notifyUpdate(null)
        else {
            userName.replace("/", "")
            pbLoading.visibility = View.VISIBLE
            SearchViewModel.getUser(userName)
        }
    }

    override fun notifyUpdate(obj: Any?) {
        pbLoading.visibility = View.GONE
        if (obj == null) {
            rvSearch.visibility = View.GONE
            tvNoResult.visibility = View.VISIBLE
        } else {
            rvSearch.visibility = View.VISIBLE
            tvNoResult.visibility = View.GONE
            searchAdapter.setList(obj as ArrayList<Any>)
        }
    }
}