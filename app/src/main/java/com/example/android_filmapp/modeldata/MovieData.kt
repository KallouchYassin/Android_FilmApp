package com.example.android_filmapp.modeldata

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieData(

    val Year:String,
    @PrimaryKey
    val Title:String,
    @SerializedName("Poster") val poster:String,
    @SerializedName("imdbID") val idmovie:String

)
