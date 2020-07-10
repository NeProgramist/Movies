package com.example.movies.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.common.Result
import com.example.movies.common.Status
import com.example.movies.domain.interactor.GetMoviesUseCase
import com.example.movies.domain.interactor.SearchMoviesUseCase
import com.example.movies.domain.model.Movie
import com.example.movies.domain.model.MoviesList
import com.example.movies.domain.repository.MoviesRepositoryImpl
import com.example.movies.framework.datasource.remote.MoviesApi
import com.example.movies.framework.datasource.remote.MoviesRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val movies = MutableLiveData<Result<MoviesList>>()

    val searchedMovie = MutableLiveData<List<Movie>>()

    fun showMovieList() {
        movies.value = Result.loading()
        this.viewModelScope.launch(Dispatchers.IO) {
            val result = GetMoviesUseCase(MoviesRepositoryImpl(
                MoviesRemoteDataSource(MoviesApi(), "b0d4f703c67f14464200a9c01a4ba190"))
            )(
                onSuccess = { movies.value = it },
                onError = { Log.e("Server error", "", it)}
            )
        }
    }

    fun showMovie() {

    }

    fun searchMovie(text: String) {
//        searchedMovie.value = Result.loading()
//        this.viewModelScope.launch(Dispatchers.IO) {
//            val result = SearchMoviesUseCase(
//                MoviesRepositoryImpl(MoviesRemoteDataSource(MoviesApi(), "adsfasdf"))
//            )(text)
//            searchedMovie.value = result
//        }
    }
}