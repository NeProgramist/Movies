package com.example.movies.data

import com.example.movies.domain.model.Movie
import com.example.movies.common.Result
import com.example.movies.domain.model.MoviesList

interface MoviesDataSource {
    fun getMovies(onSuccess: (Result<MoviesList>) -> Unit, onError: (Throwable) -> Unit)
    fun getMovie(id: Int): Result<Movie>
    fun searchMovie(text: String): Result<List<Movie>>
}