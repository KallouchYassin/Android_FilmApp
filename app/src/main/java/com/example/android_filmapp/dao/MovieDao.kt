package com.example.android_filmapp.dao

import androidx.room.*
import com.example.android_filmapp.modeldata.MovieData
import com.example.android_filmapp.modeldata.MovieDetailData


@Dao
interface MovieDao {
    @Insert
    fun insert(movieData: MovieDetailData)

    @Query("SELECT * FROM movies")
     fun getAll(): List<MovieDetailData>

    @Query("SELECT * FROM movies WHERE Title = :Title")
     fun getById(Title: String):MovieDetailData

    @Query("DELETE FROM movies")
     fun clearDb()
    @Update
     fun update(movie: MovieDetailData)

    @Delete
     fun delete(movie: MovieDetailData)
}