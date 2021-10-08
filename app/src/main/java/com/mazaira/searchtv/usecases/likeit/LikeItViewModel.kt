package com.mazaira.searchtv.usecases.likeit

import com.mazaira.searchtv.provider.room.LikesProvider
import com.mazaira.searchtv.usecases.base.BaseViewModel

class LikeItViewModel:BaseViewModel() {

    //Published
    val likesLiveData = LikesProvider.likes
}