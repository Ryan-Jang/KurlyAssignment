package com.ryan.kurlyassignment.viewmodel

import com.ryan.kurlyassignment.model.SearchAgent
import com.ryan.kurlyassignment.model.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel {
    companion object {
        private var searchAgent = SearchAgent()
        private var pageCount = 1
        private var perPage = 50
        private var observerList : ArrayList<Observer> = ArrayList()

        fun setObserver(observer : Observer) {
            observerList.add(observer)
        }

        fun removeObserver(observer : Observer) {
            if (observerList.size != 0)
                observerList.removeAt(observerList.indexOf(observer))
        }

        fun getRepoList(query : String) {
            searchAgent.getSearch(query, pageCount, perPage, object : Callback<SearchResponse> {
                override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                    if (response.isSuccessful) {
                        notifyResult(response.body()?.let { responseBody -> responseBody.repoList })

                        if (pageCount * perPage < response.body()!!.resultTotalCount)
                            pageCount++
                    } else
                        notifyResult(null)
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
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