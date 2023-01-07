package com.example.android_filmapp.modeldata

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class SearchData(

    @SerializedName("Search") val data:List<MovieData>,
    @SerializedName("totalResults") val totalData:Int,

@SerializedName("Response") val res:String
)