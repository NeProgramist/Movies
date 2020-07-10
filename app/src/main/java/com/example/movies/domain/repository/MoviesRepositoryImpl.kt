package com.example.movies.domain.repository

import com.example.movies.common.Result
import com.example.movies.data.MoviesDataSource
import com.example.movies.domain.model.Movie
import com.example.movies.domain.model.MoviesList

class MoviesRepositoryImpl(
    private val remoteDataSource: MoviesDataSource
): MoviesRepository {
    override fun getMovies(
        onSuccess: (Result<MoviesList>) -> Unit,
        onError: (Throwable) -> Unit
    ) = remoteDataSource.getMovies(onSuccess, onError)

    override fun getMovie(
        id: Int,
        onSuccess: (Result<Movie>) -> Unit,
        onError: (Throwable) -> Unit
    ) = remoteDataSource.getMovie(id, onSuccess, onError)

    override fun search(
        text: String,
        onSuccess: (Result<MoviesList>) -> Unit,
        onError: (Throwable) -> Unit
    ) = remoteDataSource.searchMovie(text, onSuccess, onError)
}
