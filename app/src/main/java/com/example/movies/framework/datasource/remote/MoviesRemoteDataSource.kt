package com.example.movies.framework.datasource.remote

import com.example.movies.common.*
import com.example.movies.data.MoviesDataSource
import com.example.movies.domain.model.DetailedMovie
import com.example.movies.domain.model.MoviesList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.Error

class MoviesRemoteDataSource(
    private val api: MoviesApi,
    private val key: String
): MoviesDataSource {

    override suspend fun getMovies(
        page: Int,
        onSuccess: (Result<MoviesList>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val call = api.getMovies(key = key, page = page)

        call.enqueue(object : Callback<MoviesList> {
            override fun onFailure(call: Call<MoviesList>, t: Throwable) {
                onError(t)
            }

            override fun onResponse(call: Call<MoviesList>, response: Response<MoviesList>) {
                if (response.isSuccessful) response.body()?.let { onSuccess(Result.success(it)) }
                else onSuccess(Result.error(Error("getMovies: ${response.errorBody()?.string()}")))
            }
        })
    }

    override suspend fun getMovie(
        id: Int,
        onSuccess: (Result<DetailedMovie>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val call = api.getMovieDetailed(key = key, id = id)

        call.enqueue(object : Callback<DetailedMovie> {
            override fun onFailure(call: Call<DetailedMovie>, t: Throwable) {
                onError(t)
            }

            override fun onResponse(call: Call<DetailedMovie>, response: Response<DetailedMovie>) {
                if (response.isSuccessful) response.body()?.let { onSuccess(Result.success(it)) }
                else onSuccess(Result.error(Error("getMovie: ${response.errorBody()?.string()}")))
            }
        })
    }

    override suspend fun searchMovie(
        page: Int,
        text: String,
        onSuccess: (Result<MoviesList>) -> Unit,
        onError: (Throwable) -> Unit) {
        val call = api.search(key = key, text = text, page = page)

        call.enqueue(object : Callback<MoviesList> {
            override fun onFailure(call: Call<MoviesList>, t: Throwable) {
                onError(t)
            }

            override fun onResponse(call: Call<MoviesList>, response: Response<MoviesList>) {
                if (response.isSuccessful) response.body()?.let { onSuccess(Result.success(it)) }
                else onSuccess(Result.error(Error("search: ${response.errorBody()?.string()}")))
            }
        })
    }

}