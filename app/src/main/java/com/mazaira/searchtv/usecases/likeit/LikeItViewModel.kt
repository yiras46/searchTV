package com.mazaira.searchtv.usecases.likeit

import com.mazaira.searchtv.model.domain.LikeEntity
import com.mazaira.searchtv.provider.room.LikesProvider
import com.mazaira.searchtv.usecases.base.BaseViewModel

class LikeItViewModel:BaseViewModel() {

    //Published
    var likes: List<LikeEntity> = arrayListOf()
        private set

    fun likes() {
        executeAction { likes = LikesProvider.getAllLikes() }
    }

}