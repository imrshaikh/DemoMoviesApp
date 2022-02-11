package com.example.moviesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.data.Movie
import com.example.moviesapp.databinding.ItemMovieBinding

/**
 * Adapter class
 */
class MovieListAdapter: PagingDataAdapter<Movie, MovieListAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.apply {
            Glide.with(poster)
                .load(ImgHelper.getImageUri(item?.posterPath))
                .placeholder(R.drawable.place_holder)
                .into(poster)

            title.text = item?.title
        }
    }

    data class ViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)


    object DiffCallback: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
    }

}