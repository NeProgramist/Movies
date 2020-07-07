package com.example.movies.framework.datasource.remote

import com.example.movies.domain.model.Movie
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("trending/movies/day")
    fun getMovies(
        @Query("api_key") key: String
    ): List<Movie>

    @GET("movie/{id}")
    fun getMovieDetailed(
        @Query("api_key") key: String,
        @Path("movie_id") id: Int
    ): Movie

    @GET("search/movie")
    fun search(
        @Query("api_key") key: String,
        @Query("query") text: String
    ): List<Movie>

    companion object Factory {
        operator fun invoke(): MoviesApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("api.themoviedb.org/3/")
                .build()

            return retrofit.create(MoviesApi::class.java);
        }
    }
}