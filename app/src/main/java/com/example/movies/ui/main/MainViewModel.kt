package com.example.movies.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movies.common.Result
import com.example.movies.domain.interactor.GetMoviesUseCase
import com.example.movies.domain.interactor.SearchMoviesUseCase
import com.example.movies.domain.model.MoviesList
import com.example.movies.domain.repository.movies.MoviesRepositoryImpl
import com.example.movies.framework.datasource.remote.MoviesApi
import com.example.movies.framework.datasource.remote.MoviesRemoteDataSource
import com.example.movies.ui.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel: BaseViewModel() {
    var pagesLoaded = 1
    val movies = MutableLiveData<Result<MoviesList>>()

    fun showMovieList(page: Int) {
        movies.value = Result.loading()

        viewModelScope.launch {
            GetMoviesUseCase(
                MoviesRepositoryImpl(
                    MoviesRemoteDataSource(MoviesApi(), API_KEY)
                )
            )(
                page,
                onSuccess = { movies.value = it },
                onError = { Log.e("Server error", "", it) }
            )
        }
    }

    fun searchMovies(text: String) {
        movies.value = Result.loading()
        Log.d("asdfasdf", "here")

        viewModelScope.launch {
            SearchMoviesUseCase(
                MoviesRepositoryImpl(
                    MoviesRemoteDataSource(MoviesApi(), API_KEY)
                )
            )(
                text,
                onSuccess = { movies.value = it },
                onError = { Log.e("Server error", "", it) }
            )
        }
    }

    companion object {
    }
}
