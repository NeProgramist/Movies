package com.example.movies.data

import com.example.movies.common.Result
import com.example.movies.domain.model.DetailedMovie
import com.example.movies.domain.model.MoviesList

interface MoviesDataSource {
    suspend fun getMovies(
        page: Int,
        onSuccess: (Result<MoviesList>) -> Unit,
        onError: (Throwable) -> Unit
    )

    suspend fun getMovie(
        id: Int,
        onSuccess: (Result<DetailedMovie>) -> Unit,
        onError: (Throwable) -> Unit
    )

    suspend fun searchMovie(
        page: Int,
        text: String,
        onSuccess: (Result<MoviesList>) -> Unit,
        onError: (Throwable) -> Unit
    )
}