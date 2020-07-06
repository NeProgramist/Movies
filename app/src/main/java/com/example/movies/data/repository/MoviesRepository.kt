package com.example.movies.data.repository

import com.example.movies.data.model.Movie

interface MoviesRepository {
    fun getMovies(): List<Movie>

    fun getMovie(id: Int): Movie
}