package com.example.android_filmapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.android_filmapp.database.MovieDatabase
import com.example.android_filmapp.databinding.ActivityMainBinding
import com.example.android_filmapp.databinding.ListDatamovieBinding
import com.example.android_filmapp.fragment.DataFragment
import com.example.android_filmapp.modeldata.MovieDetailData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity() : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var appDb : MovieDatabase
    lateinit var movie : ArrayList<MovieDetailData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding =ActivityMainBinding.inflate(layoutInflater);
        movie= ArrayList<MovieDetailData>();
        appDb= MovieDatabase.getDatabase(this);
        setContentView(binding.root);


getMovies()
        binding.btnSerach.setOnClickListener {
            showFragment();
        }

    }



    private  fun  getMovies() {


        GlobalScope.launch {


            addToLayout(appDb.movieDao().getAll() as ArrayList<MovieDetailData>)

        }



    }
    private fun addToLayout(movie:ArrayList<MovieDetailData>)
    {
        
        for (element in movie) {
            val textView = TextView(this)

            // Set the text of the TextView
            textView.text = element.Title;

            // Add the TextView to the layout


            binding.root.addView(textView);

        }


    }


    private fun showFragment() {
        val mFragmentManager=supportFragmentManager;
        val mFragmentTransaction=mFragmentManager.beginTransaction();
        val mFragment=DataFragment()

        val textsearch=binding.searchBar.text
        val mBundle=Bundle()
        mBundle.putString("searchmovie",textsearch.toString())
        mFragment.arguments=mBundle

        mFragmentTransaction.replace(R.id.fl_data,mFragment).commit();
    }
}