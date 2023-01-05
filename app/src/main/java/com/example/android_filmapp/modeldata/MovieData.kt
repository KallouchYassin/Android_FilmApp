package com.example.android_filmapp.modeldata

import com.google.gson.annotations.SerializedName

data class MovieData(
    val Year:String,
    val Title:String,
    @SerializedName("Poster") val poster:String,
    @SerializedName("imdbID") val idmovie:String

)
