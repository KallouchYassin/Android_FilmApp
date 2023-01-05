package com.example.android_filmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_filmapp.databinding.ActivityDetailMovieBinding
import com.example.android_filmapp.databinding.ActivityMainBinding

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding= ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}