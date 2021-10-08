package com.mazaira.searchtv.model.domain

import com.google.gson.annotations.SerializedName

data class SearchData(
    @SerializedName("searchType") val searchType: String,
    @SerializedName("expression") val expression: String,
    @SerializedName("results") val results: List<SearchResult>,
    val errorMessage: String
)

data class SearchResult(
    @SerializedName("id") val id: String? = null,
    @SerializedName("resultType") val resultType: String,
    @SerializedName("image") val image: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String
)

private enum class SearchType(id: Int) {
    Title(1),
    Movie(2),
    Series(4),
    Name(8),
    Episode(16),
    Company(32),
    Keyword(64),
    All(128)
}