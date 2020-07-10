package com.example.movies.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
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
        mainViewModel.movies.observe(this, moviesObserver)
        mainViewModel.searchedMovie.observe(this, searchedMoviesObserver)

        edtxt.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) mainViewModel.searchMovies(s.toString())
                else mainViewModel.showMovieList()
            }
        })

        mainViewModel.showMovieList()
    }

    private val moviesObserver = Observer<Result<MoviesList>> {
        if (it.status == Status.SUCCESS) test.text = it.data?.results.toString()
        else test.text = it.error?.message
    }

    private val searchedMoviesObserver = Observer<Result<MoviesList>> {
        if (it.status == Status.SUCCESS) test.text = it.data?.results.toString()
        else test.text = it.error?.message
    }
}
