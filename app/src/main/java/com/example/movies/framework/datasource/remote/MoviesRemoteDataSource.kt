package com.example.movies.framework.datasource.remote

import com.example.movies.common.*
import com.example.movies.data.MoviesDataSource
import com.example.movies.domain.model.Movie
import java.lang.Error

class MoviesRemoteDataSource(
    private val api: MoviesApi,
    private val key: String
): MoviesDataSource {
    override fun getMovies(): Result<List<Movie>> {
        return try {
            Result.success(api.getMovies(key = key))
        } catch(e: Error) {
            Result.error(e)
        }
    }
    override fun getMovie(id: Int): Result<Movie> {
        return try {
            Result.success(api.getMovieDetailed(key = key, id = id))
        } catch (e: Error) {
            Result.error(e)
        }
    }
    override fun searchMovie(text: String): Result<List<Movie>> {
        return try {
            Result.success(api.search(key = key, text = text))
        } catch (e : Error) {
            Result.error(e)
        }
    }
}