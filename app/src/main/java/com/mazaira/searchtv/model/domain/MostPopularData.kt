package com.mazaira.searchtv.model.domain

import com.google.gson.annotations.SerializedName

data class MostPopularData(
    @SerializedName("items") val items: List<MostPopularDetail>,
    val errorMessage: String
)

data class MostPopularDetail(
    @SerializedName("id") val id: String? = null,
    @SerializedName("rank") val rank: String,
    @SerializedName("rankUpDown") val rankUpDown: String,
    @SerializedName("title") val title: String,
    @SerializedName("fullTitle") val fullTitle: String,
    @SerializedName("year") val year: String,
    @SerializedName("image") val image: String,
    @SerializedName("crew") val crew: String,
    @SerializedName("imDbRating") val imDbRating: String,
    @SerializedName("imDbRatingCount") val imDbRatingCount: String,
    var isLike:Boolean = false
)
