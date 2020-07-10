package com.example.movies.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movies.R
import com.example.movies.domain.model.Movie
import com.example.movies.common.Result
import com.example.movies.common.Status
import com.example.movies.domain.model.MoviesList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val moviesObserver = Observer<Result<MoviesList>> {
            if (it.status == Status.SUCCESS) test.text = it.data?.results.toString()
            else test.text = it.error?.toString()
        }

        val searchedMoviesObserver =  Observer<Result<List<Movie>>> {
            // Update UI, change activity
        }

        mainViewModel.movies.observe(this, moviesObserver)
//        mainViewModel.searchedMovie.observe(this, searchedMoviesObserver)

        mainViewModel.showMovieList()
    }
}
