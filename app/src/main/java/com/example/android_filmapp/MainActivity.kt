package com.example.android_filmapp

import android.content.Context
import android.content.Intent
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding =ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);
        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.liked -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)

                    return@setOnNavigationItemSelectedListener true
                }
                // Add other navigation items here
            }
            true
        }






        binding.btnSerach.setOnClickListener {
            showFragment();
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