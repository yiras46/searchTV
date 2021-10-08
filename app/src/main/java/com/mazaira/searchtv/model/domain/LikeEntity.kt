package com.mazaira.searchtv.model.domain

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "LikeEntityTable", indices = [Index(value = ["id"], unique = true)])
data class LikeEntity(
    @PrimaryKey(autoGenerate = true) var _id: Long = 0,
    val id:String,
    val title: String,
    val fullTitle: String,
    val image: String,
    val score:String,
    val dateLike:String,
    val starsCrew:String
)
