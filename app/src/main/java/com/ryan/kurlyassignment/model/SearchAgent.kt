package com.ryan.kurlyassignment.model

import retrofit2.Callback

class SearchAgent : HttpAgent() {
    fun getSearch(searchQuery : String, page : Int, perPage: Int, callback : Callback<SearchResponse>) {
        getRequestServiceInstance().searchRepo(searchQuery, page, perPage).enqueue(callback)
    }
}