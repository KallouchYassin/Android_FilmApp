package com.example.android_filmapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_filmapp.DetailMovieActivity
import com.example.android_filmapp.databinding.ListDatamovieBinding
import com.example.android_filmapp.databinding.ListLikedDatamovieBinding
import com.example.android_filmapp.modeldata.MovieData
import com.example.android_filmapp.modeldata.MovieDetailData

class LikedMovieAdapter (private val listMovie:ArrayList<MovieDetailData>, private val context: Context): RecyclerView.Adapter<LikedMovieAdapter.MovieViewHolder>() {
    inner class MovieViewHolder(itemView: ListLikedDatamovieBinding): RecyclerView.ViewHolder(itemView.root){

        private val binding=itemView;
        fun bind(movieData: MovieDetailData){
            with(binding){
                Glide.with(itemView).load(movieData.poster).into(imgPoster2);
                tvTitle2.text=movieData.Title;
                tvYear2.text=movieData.Year;

                cvIdmovie2.setOnClickListener{
                    var i= Intent(context, DetailMovieActivity::class.java).apply { putExtra("imdbid",movieData.idmovie) }
                    context.startActivity(i);
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ListLikedDatamovieBinding.inflate(
                LayoutInflater.from(parent.context),
            parent,false
        ))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int=listMovie.size
}