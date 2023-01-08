package com.example.android_filmapp

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.android_filmapp.database.MovieDatabase
import com.example.android_filmapp.modeldata.MovieDetailData
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test

class DatabaseTest {
    private lateinit var database: MovieDatabase

    @Before
    fun createDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MovieDatabase::class.java
        ).build()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun insertAndGetUser() {
        // Create a new user
        val movie = MovieDetailData(Year = "John", Title = "30", Release ="released" , poster ="poster", idmovie = "id",plot="plot");
        // Insert the user in the database
        database.movieDao().insert(movie)

        // Get the user from the database
        val retrievedUser = database.movieDao().getById(movie.Title)

        // Make sure the retrieved user is the same as the original user
        assertEquals(movie, retrievedUser)
    }
}