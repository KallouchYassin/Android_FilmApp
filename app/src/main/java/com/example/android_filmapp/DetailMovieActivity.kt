package com.example.android_filmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.android_filmapp.IntanceClient.RClient
import com.example.android_filmapp.databinding.ActivityDetailMovieBinding
import com.example.android_filmapp.databinding.ActivityMainBinding
import com.example.android_filmapp.modeldata.MovieDetailData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailMovieBinding
    var b:Bundle?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding= ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        b=intent.extras
        val i=b?.getString("imdbid")
        val apikey="6690459f"

        RClient.instances.getDetailMovie(i,apikey).enqueue(object :Callback<MovieDetailData> {
            override fun onResponse(
                call: Call<MovieDetailData>,
                response: Response<MovieDetailData>
            ) {
                binding.tvHeadingmovie.text = response.body()?.Title
                binding.tvYear.text = response.body()?.Year
                binding.tvType.text = response.body()?.Release
                Glide.with(this@DetailMovieActivity).load(response.body()?.poster)
                    .into(binding.imgPoster)
            }

            override fun onFailure(call: Call<MovieDetailData>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

}