package com.example.movies.data

import com.example.movies.domain.model.Movie
import com.example.movies.common.Result

interface MoviesDataSource {
    fun getMovies(): Result<List<Movie>>
    fun getMovie(id: Int): Result<Movie>
    fun searchMovie(text: String): Result<List<Movie>>
}