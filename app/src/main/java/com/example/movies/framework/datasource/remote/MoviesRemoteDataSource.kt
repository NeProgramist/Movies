package com.example.movies.framework.datasource.remote

import com.example.movies.data.MoviesDataSource
import com.example.movies.domain.model.Movie
import com.example.movies.framework.datasource.remote.MoviesApi

class MoviesRemoteDataSource(
    private val api: MoviesApi,
    private val key: String
): MoviesDataSource {
    override fun getMovies(): List<Movie> = api.getMovies(key = key)
    override fun getMovie(id: Int): Movie = api.getMovieDetailed(id = id, key = key)

}