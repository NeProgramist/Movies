package com.example.movies.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.domain.model.Movie

class MoviesAdapter(
    private val onItemClick: OnItemClickListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var movies = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val item = layoutInflater.inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(item, onItemClick)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieViewHolder) {
            holder.bind(movies[position], position)
        }
    }

    fun clear() {
        val size = movies.size
        movies.clear()
        notifyItemRangeRemoved(0, size)
    }

    fun insertMovie(data: Movie, position: Int) {
        movies[position] = data
        notifyItemChanged(position)
    }

    fun setupMovies(data: List<Movie>) {
        val startIndex = movies.size - 1
        movies.addAll(data)
        notifyItemRangeInserted(startIndex, data.size)
    }

    inner class MovieViewHolder(
        private val item: View,
        private val onItemClick: OnItemClickListener
    ): RecyclerView.ViewHolder(item) {
        private val posterId = item.findViewById<ImageView>(R.id.img)
        private val nameId = item.findViewById<TextView>(R.id.movie_name)
        private val description = item.findViewById<TextView>(R.id.movie_description)

        fun bind(movie: Movie, id: Int) {
            item.id = id

            if (movie.image != null) posterId.setImageBitmap(movie.image)
            else posterId.setImageDrawable(item.context.getDrawable(R.drawable.ic_loading_error))

            nameId.text =
                if (!movie.original_title.isNullOrEmpty()) movie.original_title
                else if (!movie.title.isNullOrEmpty()) movie.title
                else "[No title]"

            description.text = movie.overview

            item.setOnClickListener {
                onItemClick.onClickListener(movies[adapterPosition].id)
            }
        }
    }

    interface OnItemClickListener {
        fun onClickListener(id: Int)
    }
}
