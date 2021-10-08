package com.mazaira.searchtv.model.domain

import com.google.gson.annotations.SerializedName

data class TopData(
    @SerializedName("items") val items: List<TopDataDetail>,
    val errorMessage: String
)

data class TopDataDetail(
    @SerializedName("id") val id: String? = null,
    @SerializedName("rank") val rank: String,
    @SerializedName("title") val title: String,
    @SerializedName("fullTitle") val fullTitle: String,
    @SerializedName("year") val year: String,
    @SerializedName("image") val image: String,
    @SerializedName("crew") val crew: String,
    @SerializedName("imDbRating") val imDbRating: String,
    @SerializedName("imDbRatingCount") val imDbRatingCount: String
)


