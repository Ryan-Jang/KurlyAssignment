package com.ryan.kurlyassignment.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("total_count")
    val resultTotalCount : Int,
    @SerializedName("items")
    val repoList : List<SearchModel>
)