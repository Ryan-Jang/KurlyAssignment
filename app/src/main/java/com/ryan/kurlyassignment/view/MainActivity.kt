package com.ryan.kurlyassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ryan.kurlyassignment.R
import com.ryan.kurlyassignment.model.SearchModel
import com.ryan.kurlyassignment.viewmodel.SearchViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener, SearchViewModel.Observer {
    private lateinit var etSearch : EditText
    private lateinit var btnSearch : Button
    private lateinit var rvSearch : RecyclerView
    private lateinit var tvNoResult : TextView
    private lateinit var pbLoading : ProgressBar

    private lateinit var searchAdapter : SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etSearch = findViewById(R.id.etSearch)
        btnSearch = findViewById(R.id.btnSearch)
        rvSearch = findViewById(R.id.rvSearch)
        tvNoResult = findViewById(R.id.tvNoResult)
        pbLoading = findViewById(R.id.pbLoading)

        btnSearch.setOnClickListener(this)

        initList()
    }

    private fun initList() {
        searchAdapter = SearchAdapter()
        rvSearch.adapter = searchAdapter
        rvSearch.addItemDecoration(
            DividerItemDecoration(this, LinearLayout.VERTICAL).apply {
                getDrawable(R.drawable.shape_divider)?.let {
                    setDrawable(it)
                }
        })
        rvSearch.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount -1

                if (lastVisibleItemPosition == itemTotalCount) {
                    requestRepoList()
                }
            }
        })
    }

    private fun requestRepoList() {
        if ("".equals(etSearch.text))
            Toast.makeText(this, getString(R.string.search_hint), Toast.LENGTH_SHORT).show()
        else {
            pbLoading.visibility = View.VISIBLE
            SearchViewModel.getRepoList(etSearch.text.toString())
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
            searchAdapter.setList(obj as ArrayList<SearchModel>)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btnSearch -> requestRepoList()
        }
    }
}