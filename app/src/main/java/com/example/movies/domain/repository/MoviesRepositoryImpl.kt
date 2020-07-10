package com.example.movies.domain.repository

import com.example.movies.common.Result
import com.example.movies.data.MoviesDataSource
import com.example.movies.domain.model.Movie
import com.example.movies.domain.model.MoviesList

class MoviesRepositoryImpl(
    private val remoteDataSource: MoviesDataSource
): MoviesRepository {
    override fun getMovies(onSuccess: (Result<MoviesList>) -> Unit, onError: (Throwable) -> Unit) =
        remoteDataSource.getMovies(onSuccess, onError)


    override fun getMovie(id: Int): Result<Movie> = remoteDataSource.getMovie(id = id)

    override fun search(text: String): Result<List<Movie>> = remoteDataSource.searchMovie(text = text)

}