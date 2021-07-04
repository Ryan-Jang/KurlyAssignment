package com.ryan.kurlyassignment.viewmodel

import com.ryan.kurlyassignment.model.SearchAgent
import com.ryan.kurlyassignment.model.SearchModel
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

        private var resultList : ArrayList<SearchModel> = ArrayList()
        private var totalCount = -1

        fun setObserver(observer : Observer) {
            observerList.add(observer)
        }

        fun removeObserver(observer : Observer) {
            if (observerList.size != 0)
                observerList.removeAt(observerList.indexOf(observer))
        }

        fun getRepoList(query : String) {
            if (totalCount != -1) {
                if (pageCount * perPage >= totalCount) {
                    notifyResult(false)
                    return
                }
            }

            searchAgent.getSearch(query, pageCount, perPage, object : Callback<SearchResponse> {
                override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                    if (response.isSuccessful) {
                        totalCount = response.body()!!.resultTotalCount
                        response.body()!!.repoList?.let { resultList.addAll(it) }
                        notifyResult(resultList)

                        if (pageCount * perPage < totalCount)
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