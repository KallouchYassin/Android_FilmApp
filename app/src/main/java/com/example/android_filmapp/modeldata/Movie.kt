package com.example.android_filmapp.modeldata

import android.media.Rating
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Movie(
    @ColumnInfo(name = "imdbID")
    @PrimaryKey
val imdbID: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "year")
    val year: String,
    @ColumnInfo(name = "poster")
    val poster: String,




)
