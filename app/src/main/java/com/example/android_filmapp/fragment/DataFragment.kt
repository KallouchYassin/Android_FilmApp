package com.example.android_filmapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_filmapp.Adapter.MovieAdapter
import com.example.android_filmapp.IntanceClient.RClient
import com.example.android_filmapp.R
import com.example.android_filmapp.databinding.FragmentDataBinding
import com.example.android_filmapp.modeldata.MovieData
import com.example.android_filmapp.modeldata.SearchData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DataFragment : Fragment() {

    private var _binding: FragmentDataBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private val list =ArrayList<MovieData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDataBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvData.setHasFixedSize(true)
        binding.rvData.layoutManager=LinearLayoutManager(context)
        val bundle=arguments
        val s=bundle?.getString("searchmovie")
        val apikey="6690459f"

        binding.progressBar.visibility


        RClient.instances.getMovies(s,apikey).enqueue(object : Callback<SearchData> {
            override fun onResponse(call: Call<SearchData>, response: Response<SearchData>) {
            //    val  responseCode=response.code()
                val checkResponse=response.body()?.res
                if(checkResponse=="True")
                {
                    response.body()?.let{list.addAll(it.data)}
                    val adapter=MovieAdapter(list,requireContext());
                    binding.rvData.adapter=adapter;
                    binding.progressBar.isVisible=false
                }else{
                    Toast.makeText(context,"Movie not found",Toast.LENGTH_LONG).show()
                    binding.progressBar.isVisible=false
                }

            }

            override fun onFailure(call: Call<SearchData>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}