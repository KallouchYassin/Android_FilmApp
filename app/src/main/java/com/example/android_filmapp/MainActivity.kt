package com.example.android_filmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_filmapp.databinding.ActivityMainBinding
import com.example.android_filmapp.fragment.DataFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding =ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

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