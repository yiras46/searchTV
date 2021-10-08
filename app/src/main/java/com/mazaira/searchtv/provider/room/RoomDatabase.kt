package com.mazaira.searchtv.provider.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mazaira.searchtv.model.domain.LikeEntity

@Database(entities = [LikeEntity::class], version = 1)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun likesDao(): LikesDao
}