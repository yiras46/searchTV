package com.mazaira.searchtv.model.domain

import com.google.gson.annotations.SerializedName

data class NewData(
    @SerializedName("items") val items: List<NewDataDetail>,
    val errorMessage: String
)

data class NewDataDetail(
    @SerializedName("id") val id: String? = null,
    @SerializedName("title") val title: String,
    @SerializedName("fullTitle") val fullTitle: String,
    @SerializedName("year") val year: String,
    @SerializedName("image") val image: String,
    @SerializedName("releaseState") val releaseState: String,
    @SerializedName("plot") val plot: String,
    @SerializedName("stars") val stars: String,
    @SerializedName("imDbRating") val imDbRating: String,
    @SerializedName("metacriticRating") val metacriticRating: String,
    @SerializedName("genreList") val genreList: Array<Map<String, String>>,
    var isLike:Boolean = false
)
