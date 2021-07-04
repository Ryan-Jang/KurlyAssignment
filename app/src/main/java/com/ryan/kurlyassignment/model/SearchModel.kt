package com.ryan.kurlyassignment.model

import com.google.gson.annotations.SerializedName

data class SearchModel(
    @SerializedName("full_name")
    val repoName : String,
    @SerializedName("description")
    val repoDescription : String,
    @SerializedName("language")
    val codeLanguage : String
)