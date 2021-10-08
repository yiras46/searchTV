package com.mazaira.searchtv.usecases.movies

import com.mazaira.searchtv.model.domain.MostPopularDetail
import com.mazaira.searchtv.model.domain.SearchResult
import com.mazaira.searchtv.provider.api.ImdbProvider
import com.mazaira.searchtv.provider.room.LikesProvider
import com.mazaira.searchtv.usecases.base.BaseViewModel

class MoviesViewModel : BaseViewModel() {

    //Public
    var search: List<SearchResult> = arrayListOf()
        private set
    var mostPopularMovies: List<MostPopularDetail> = arrayListOf()
        private set

    fun query(query: String) {
        if (query.isNotEmpty()) {
            executeAction { search = ImdbProvider.search(query).results }
        }
    }

    fun mostPopularMovies() {
        executeAction {

            val movies = ImdbProvider.mostPopularMovies().items

            //Find movies at Room and checkit with like
            LikesProvider.getAllLikes().let { likes ->
                likes.forEach { like ->
                    movies.find {
                        it.id!!.lowercase().contentEquals(like.id.lowercase())
                    }?.let {
                        it.isLike = true
                    }
                }
            }
            mostPopularMovies = movies
        }
    }
}