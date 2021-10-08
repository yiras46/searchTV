package com.mazaira.searchtv.provider.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.mazaira.searchtv.SearchTvApplication
import com.mazaira.searchtv.model.domain.LikeEntity
import com.mazaira.searchtv.util.exceptions.LikeExceptions
import com.mazaira.searchtv.util.exceptions.TypeError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object LikesProvider {

    suspend fun getAllLikes(): List<LikeEntity> {
        return SearchTvApplication.database.likesDao().getAllLikes()
    }

    suspend fun addLike(likeEntity: LikeEntity) = withContext(Dispatchers.IO){
        val result = SearchTvApplication.database.likesDao().addLike(likeEntity)
        if(result <= 0L) throw LikeExceptions(TypeError.INSERT)
    }

    suspend fun deleteLike(likeEntity: LikeEntity) = withContext(Dispatchers.IO){
        val result = SearchTvApplication.database.likesDao().deleteLike(likeEntity)
        if(result <= 0) throw LikeExceptions(TypeError.DELETE)
    }

    suspend fun updateStore(likeEntity: LikeEntity) = withContext(Dispatchers.IO){
        val result = SearchTvApplication.database.likesDao().updateLike(likeEntity)
        if(result <= 0) throw LikeExceptions(TypeError.UPDATE)
    }

}