package com.mazaira.searchtv.usecases.comingsoon

import com.mazaira.searchtv.model.domain.NewDataDetail
import com.mazaira.searchtv.provider.api.ImdbProvider
import com.mazaira.searchtv.provider.room.LikesProvider
import com.mazaira.searchtv.usecases.base.BaseViewModel

class ComingSoonViewModel:BaseViewModel() {

    //Published
    var comingSoonMovies: List<NewDataDetail> = arrayListOf()
        private set

    fun comingSoon() {
        executeAction {
            val comingMovies = ImdbProvider.comingSoon().items

            //Find TVShow at Room and checkit with like
            LikesProvider.getAllLikes().let { likes ->
                likes.forEach { like ->
                    comingMovies.find {
                        it.id!!.lowercase().contentEquals(like.id.lowercase())
                    }?.let {
                        it.isLike = true
                    }
                }
            }

            comingSoonMovies = comingMovies
        }
    }

}