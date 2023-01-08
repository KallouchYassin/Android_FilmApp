package com.example.android_filmapp.modeldata

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "movies")
data class MovieDetailData(
    val Year:String,
    @PrimaryKey
    val Title:String,
    @SerializedName("Released") val Release:String,
    @SerializedName("Poster") val poster:String,
    @SerializedName("Plot") val plot:String,
    @SerializedName("imdbID") val idmovie:String


)
