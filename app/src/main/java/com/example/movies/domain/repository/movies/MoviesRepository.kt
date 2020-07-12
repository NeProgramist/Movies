package com.example.movies.domain.repository.movies

import com.example.movies.common.Result
import com.example.movies.domain.model.DetailedMovie
import com.example.movies.domain.model.MoviesList

interface MoviesRepository {
    suspend fun getMovies(onSuccess: (Result<MoviesList>) -> Unit, onError: (Throwable) -> Unit)
    suspend fun getMovie(id: Int, onSuccess: (Result<DetailedMovie>) -> Unit, onError: (Throwable) -> Unit)
    suspend fun search(text: String, onSuccess: (Result<MoviesList>) -> Unit, onError: (Throwable) -> Unit)
}
