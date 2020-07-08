package com.example.movies.domain.repository

import com.example.movies.data.MoviesDataSource
import com.example.movies.domain.model.Movie

class MoviesRepositoryImpl(
    private val remoteDataSource: MoviesDataSource
): MoviesRepository {
    override fun getMovies(): List<Movie> = remoteDataSource.getMovies()
    override fun getMovie(id: Int): Movie = remoteDataSource.getMovie(id = id)
    override fun search(text: String): List<Movie> = remoteDataSource.searchMovie(text = text)
}