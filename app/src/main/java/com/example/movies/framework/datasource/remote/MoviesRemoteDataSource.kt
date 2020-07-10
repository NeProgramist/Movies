package com.example.movies.framework.datasource.remote

import com.example.movies.common.*
import com.example.movies.data.MoviesDataSource
import com.example.movies.domain.model.Movie
import com.example.movies.domain.model.MoviesList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.Error

class MoviesRemoteDataSource(
    private val api: MoviesApi,
    private val key: String
): MoviesDataSource {

    override fun getMovies(onSuccess: (Result<MoviesList>) -> Unit, onError: (Throwable) -> Unit) {
        val call = api.getMovies(key = key)

        call.enqueue(object : Callback<MoviesList> {
            override fun onFailure(call: Call<MoviesList>, t: Throwable) {
                onError(t)
            }

            override fun onResponse(call: Call<MoviesList>, response: Response<MoviesList>) {
                if (response.isSuccessful) response.body()?.let { onSuccess(Result.success(it)) }
                else onSuccess(Result.error(Error("Troubles with response")))

            }

        })

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