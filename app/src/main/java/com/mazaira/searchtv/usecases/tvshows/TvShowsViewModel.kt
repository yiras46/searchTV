package com.mazaira.searchtv.usecases.tvshows

import com.mazaira.searchtv.model.domain.LikeEntity
import com.mazaira.searchtv.model.domain.MostPopularDetail
import com.mazaira.searchtv.model.domain.SearchResult
import com.mazaira.searchtv.provider.api.ImdbProvider
import com.mazaira.searchtv.provider.room.LikesProvider
import com.mazaira.searchtv.usecases.base.BaseViewModel

class TvShowsViewModel : BaseViewModel() {

    //Published
    var search: List<SearchResult> = arrayListOf()
        private set
    var mostPopularTVs: List<MostPopularDetail> = arrayListOf()
        private set

    fun mostPopularTVs() {
        executeAction {

            val tvSeries = ImdbProvider.mostPopularTvs().items

            //Find TVShow at Room and checkit with like
            LikesProvider.getAllLikes().let { likes ->
                likes.forEach { like ->
                    tvSeries.find {
                        it.id!!.lowercase().contentEquals(like.id.lowercase())
                    }?.let {
                        it.isLike = true
                    }
                }
            }
            mostPopularTVs = tvSeries
        }
    }
}