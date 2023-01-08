package com.example.android_filmapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.android_filmapp.IntanceClient.RClient
import com.example.android_filmapp.database.MovieDatabase
import com.example.android_filmapp.databinding.ActivityDetailMovieBinding
import com.example.android_filmapp.modeldata.MovieDetailData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailMovieBinding
    var b:Bundle?=null
    private lateinit var appDb : MovieDatabase
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding= ActivityDetailMovieBinding.inflate(layoutInflater);
        appDb= MovieDatabase.getDatabase(this);
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
                binding.tvTitle.text=response.body()?.Title
                binding.tvPlot.text=response.body()?.plot
                Glide.with(this@DetailMovieActivity).load(response.body()?.poster)
                    .into(binding.imgPoster)
                binding.imgToolbarBtnFav.setOnClickListener(){
                    addToFav(response.body());
                }

            }

            override fun onFailure(call: Call<MovieDetailData>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        binding.imgToolbarBtnBack.setOnClickListener {
            finish()
        }

    }

    private fun addToFav(body: MovieDetailData?) {
        GlobalScope.launch(Dispatchers.IO) {
            if (body?.let { appDb.movieDao().getById(it.Title)} !=null ) {
                binding.imgToolbarBtnFav.setBackgroundResource(R.drawable.btn_bg3);
                    return@launch;
            }else{
                binding.imgToolbarBtnFav.setBackgroundResource(R.drawable.btn_bg3);
                body?.let { appDb.movieDao().insert(it) }
            }
        }

    }

}