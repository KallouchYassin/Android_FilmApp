package com.example.android_filmapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_filmapp.database.MovieDatabase
import com.example.android_filmapp.databinding.ActivityHomeBinding
import com.example.android_filmapp.fragment.DataFragment
import com.example.android_filmapp.fragment.LikedDataFragment
import com.example.android_filmapp.modeldata.MovieDetailData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var appDb: MovieDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater);
        setContentView(binding.root);
        appDb = MovieDatabase.getDatabase(this);
        getMovies()
        binding.bottomNavHome.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.search -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }

                // Add other navigation items here
            }
            false
        }

    }






    private fun getMovies() {
        val mFragmentManager=supportFragmentManager;
        val mFragmentTransaction=mFragmentManager.beginTransaction();
        val mFragment= LikedDataFragment()



        mFragmentTransaction.replace(R.id.fl_data2,mFragment).commit();




    }
}