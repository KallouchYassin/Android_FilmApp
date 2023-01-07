package com.example.android_filmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_filmapp.Adapter.MovieAdapter
import com.example.android_filmapp.database.MovieDatabase
import com.example.android_filmapp.databinding.ActivityMainBinding
import com.example.android_filmapp.databinding.FragmentDataBinding
import com.example.android_filmapp.fragment.DataFragment
import com.example.android_filmapp.modeldata.Movie
import com.example.android_filmapp.modeldata.MovieData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var appDb : MovieDatabase
    lateinit var movie : ArrayList<MovieData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding =ActivityMainBinding.inflate(layoutInflater);
        movie= ArrayList<MovieData>();
        appDb= MovieDatabase.getDatabase(this);
        setContentView(binding.root);

        addMovies();

        binding.btnSerach.setOnClickListener {
            showFragment();
        }

    }

    private fun addMovies() {


            GlobalScope.launch(Dispatchers.IO) {
                if (appDb.movieDao().getAll().isEmpty()) {


                    val movie = MovieData(
                        "1993",
                        "Jurassic Park",
                        "https://m.media-amazon.com/images/M/MV5BMjM2MDgxMDg0Nl5BMl5BanBnXkFtZTgwNTM2OTM5NDE@._V1_SX300.jpg",
                        "tt0107290"
                    );

                    val movie1 = MovieData(
                        "2015",
                        "Jurassic World",
                        "https://m.media-amazon.com/images/M/MV5BNzQ3OTY4NjAtNzM5OS00N2ZhLWJlOWUtYzYwZjNmOWRiMzcyXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg",
                        "tt0369610"
                    );
                    val movie2 = MovieData(
                        "1997",
                        "The Lost World: Jurassic Park",
                        "https://m.media-amazon.com/images/M/MV5BMDFlMmM4Y2QtNDg1ZS00MWVlLTlmODgtZDdhYjY5YjdhN2M0XkEyXkFqcGdeQXVyNTI4MjkwNjA@._V1_SX300.jpg",
                        "tt0119567"
                    );
                    val movie3 = MovieData(
                        "2001",
                        "Jurassic Park III",
                        "https://m.media-amazon.com/images/M/MV5BZDMyZGJjOGItYjJkZC00MDVlLWE0Y2YtZGIwMDExYWE3MGQ3XkEyXkFqcGdeQXVyNDYyMDk5MTU@._V1_SX300.jpg",
                        "tt0163025"
                    );
                appDb.movieDao().insert(movie)
                appDb.movieDao().insert(movie1)
                appDb.movieDao().insert(movie2)
                appDb.movieDao().insert(movie3)

                }else{
                    getMovies();
                }
        }









    }

    private  fun  getMovies() {


        GlobalScope.launch {






addToLayout(appDb.movieDao().getAll() as ArrayList<MovieData>)

        }



    }
    private fun addToLayout(movie:ArrayList<MovieData>)
    {
        for (element in movie) {
            // Create a TextView for the element
            val textView = TextView(this)

            // Set the text of the TextView
            textView.text = element.Title

            // Add the TextView to the layout
            binding?.root!!.addView(textView)


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