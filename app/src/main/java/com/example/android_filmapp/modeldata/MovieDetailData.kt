package com.example.android_filmapp.modeldata

import com.google.gson.annotations.SerializedName

data class MovieDetailData(
    val Year:String,
    val Title:String,
    @SerializedName("Released") val Release:String,
    @SerializedName("Poster") val poster:String


)
