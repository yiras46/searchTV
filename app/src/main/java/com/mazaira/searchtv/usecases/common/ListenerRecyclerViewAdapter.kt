package com.mazaira.searchtv.usecases.common

import com.mazaira.searchtv.model.domain.LikeEntity

interface ListenerRecyclerViewAdapter {
    fun onFavorite(isFavorite:Boolean, likeEntity: LikeEntity) {}
}