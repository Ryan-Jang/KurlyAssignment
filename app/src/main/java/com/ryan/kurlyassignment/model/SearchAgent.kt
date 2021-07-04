package com.ryan.kurlyassignment.model

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query

class SearchAgent : HttpAgent() {
    fun getSearch(searchQuery : String, page : Int, callback : Callback<SearchResponse>) {
        getRequestServiceInstance(SearchService::class.java).searchRepo(searchQuery, page).enqueue(callback)
    }

    interface SearchService {
        @GET("repositories?per_page=10")
        fun searchRepo(@Query("q") query: String, @Query("page") page: Int = 1) : Call<SearchResponse>
    }
}