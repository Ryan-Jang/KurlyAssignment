package com.ryan.kurlyassignment.model

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query

class SearchAgent : HttpAgent() {
    init {
        baseUrl = "https://api.github.com/search/"
    }

    fun getSearch(searchQuery : String, page : Int, perPage: Int, callback : Callback<SearchResponse>) {
        getRequestServiceInstance(SearchService::class.java).searchRepo(searchQuery, page, perPage).enqueue(callback)
    }

    interface SearchService {
        @GET("repositories")
        fun searchRepo(@Query("q") query: String, @Query("page") page: Int = 1, @Query("per_page") perPage : Int = 50) : Call<SearchResponse>
    }
}