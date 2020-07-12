package com.example.movies.framework.datasource.remote

import com.example.movies.domain.model.DetailedMovie
import com.example.movies.domain.model.Movie
import com.example.movies.domain.model.MoviesList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("trending/movies/week")
    fun getMovies(
        @Query("api_key") key: String
    ): Call<MoviesList>

    @GET("movie/{movie_id}")
    fun getMovieDetailed(
        @Path("movie_id") id: Int,
        @Query("api_key") key: String,
        @Query("append_to_response") request: String = "credits"
    ): Call<DetailedMovie>

    @GET("search/movie")
    fun search(
        @Query("api_key") key: String,
        @Query("query") text: String
    ): Call<MoviesList>

    companion object Factory {
        operator fun invoke(): MoviesApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.themoviedb.org/3/")
                .build()

            return retrofit.create(MoviesApi::class.java);
        }

    }
}