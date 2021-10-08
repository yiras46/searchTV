package com.mazaira.searchtv

import android.app.Application
import androidx.room.Room
import com.mazaira.searchtv.provider.room.RoomDatabase

class SearchTvApplication :Application() {

    companion object{
        lateinit var database: RoomDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this,
            RoomDatabase::class.java,
            "RoomDatabase")
            .build()
    }

}