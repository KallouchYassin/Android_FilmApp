package com.example.android_filmapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.android_filmapp.Adapter.LikedMovieAdapter
import com.example.android_filmapp.Adapter.MovieAdapter
import com.example.android_filmapp.dao.MovieDao
import com.example.android_filmapp.database.MovieDatabase
import com.example.android_filmapp.databinding.FragmentLikedDataBinding
import com.example.android_filmapp.modeldata.MovieData
import com.example.android_filmapp.modeldata.MovieDetailData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LikedDataFragment : Fragment() {
    private var _binding: FragmentLikedDataBinding?=null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private var list =ArrayList<MovieDetailData>()
    private lateinit var appDb: MovieDatabase
    private lateinit var myDao: MovieDao


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        appDb = Room.databaseBuilder(requireContext(), MovieDatabase::class.java, "movies2")
            .build()
myDao=appDb.movieDao();
        _binding=FragmentLikedDataBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvData2.setHasFixedSize(true)

        binding.rvData2.layoutManager=LinearLayoutManager(context)

        binding.progressBar2.visibility



        GlobalScope.launch {

            list= myDao.getAll() as ArrayList<MovieDetailData>;


            val adapter=LikedMovieAdapter(list,requireContext());
            binding.rvData2.adapter=adapter;
            binding.progressBar2.isVisible=false
            binding.progressBar2.isVisible=false
        }




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}