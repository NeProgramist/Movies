package com.example.movies.ui.main

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.core.view.children
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.common.RVState
import com.example.movies.common.Result
import com.example.movies.domain.interactor.GetMoviesUseCase
import com.example.movies.domain.interactor.SearchMoviesUseCase
import com.example.movies.domain.model.MoviesList
import com.example.movies.domain.repository.movies.MoviesRepositoryImpl
import com.example.movies.framework.datasource.remote.MoviesApi
import com.example.movies.framework.datasource.remote.MoviesRemoteDataSource
import com.example.movies.ui.BaseViewModel
import kotlinx.coroutines.*

class MainViewModel: BaseViewModel() {
    var pagesLoaded = 1
    var rvState = RVState.TRENDING
    var currentSearch = MutableLiveData<String>()
    val movies = MutableLiveData<Result<MoviesList>>()
    private var job: Job? = null

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

    fun searchMovies(text: String, page: Int) {
        movies.value = Result.loading()

        viewModelScope.launch {
            SearchMoviesUseCase(
                MoviesRepositoryImpl(
                    MoviesRemoteDataSource(MoviesApi(), API_KEY)
                )
            )(
                page,
                text,
                onSuccess = { movies.value = it },
                onError = { Log.e("Server error", "", it) }
            )
        }
    }

    val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val lastId = recyclerView.children.last().id
            if (lastId >= pagesLoaded * 15) {
                when(rvState) {
                    RVState.TRENDING -> showMovieList(++pagesLoaded)
                    RVState.SEARCH -> searchMovies(currentSearch.value ?: "", ++pagesLoaded)
                }
            }
        }
    }

    val onTextChangedListener = object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            GlobalScope.launch {
                job?.cancelAndJoin()
                job = CoroutineScope(Dispatchers.Main).launch {
                    delay(500)
                    currentSearch.value = s.toString()
                }
            }
        }

    }

}
