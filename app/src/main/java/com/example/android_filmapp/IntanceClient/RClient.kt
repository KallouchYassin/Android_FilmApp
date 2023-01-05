package com.example.android_filmapp.IntanceClient

import com.example.android_filmapp.`interface`.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RClient {

    private const val BASE_URL="https://www.omdbapi.com/"
    val instances:Api by lazy{

        val retrofit=Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        retrofit.create(Api::class.java)
    }
}