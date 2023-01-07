package com.example.android_filmapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android_filmapp.dao.MovieDao
import com.example.android_filmapp.modeldata.Movie
import com.example.android_filmapp.modeldata.MovieData
import com.example.android_filmapp.modeldata.MovieDetailData
import com.example.android_filmapp.modeldata.SearchData


@Database(entities = [MovieDetailData::class],version = 2, exportSchema = false)

abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao() : MovieDao

    companion object{

        @Volatile
        private var INSTANCE : MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase{

            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movies2"
                ).build()
                INSTANCE = instance
                return instance
            }

        }

    }
}