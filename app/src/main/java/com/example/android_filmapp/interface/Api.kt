package com.example.android_filmapp.`interface`

import com.example.android_filmapp.modeldata.MovieDetailData
import com.example.android_filmapp.modeldata.SearchData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/")
    fun getMovies(
        @Query("s")s:String?,
        @Query("apikey") apikey:String
    ): Call<SearchData>

    @GET("/")
    fun getDetailMovie(
        @Query("i") title:String?,
        @Query("apikey") apikey:String,
    ):Call<MovieDetailData>
}