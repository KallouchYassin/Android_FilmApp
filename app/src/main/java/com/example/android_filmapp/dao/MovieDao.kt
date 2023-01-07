package com.example.android_filmapp.dao

import androidx.room.*
import com.example.android_filmapp.modeldata.MovieData


@Dao
interface MovieDao {
    @Insert
    fun insert(movieData: MovieData)

    @Query("SELECT * FROM movies")
     fun getAll(): List<MovieData>



    @Query("DELETE FROM movies")
     fun clearDb()
    @Update
     fun update(movie: MovieData)

    @Delete
     fun delete(movie: MovieData)
}