package com.mazaira.searchtv.provider.room

import androidx.room.*
import com.mazaira.searchtv.model.domain.LikeEntity

@Dao
interface LikesDao {

    @Query("SELECT * FROM LikeEntityTable")
    suspend fun getAllLikes() : List<LikeEntity>

    @Query("SELECT * FROM LikeEntityTable where id = :id")
    suspend fun getLikeById(id: String): LikeEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLike(likeEntity: LikeEntity): Long

    @Update
    suspend fun updateLike(likeEntity: LikeEntity):Int

    @Delete
    suspend fun deleteLike(likeEntity: LikeEntity):Int
}