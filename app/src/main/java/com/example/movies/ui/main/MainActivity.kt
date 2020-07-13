package com.example.movies.ui.main

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.R
import com.example.movies.common.ImageSizes
import com.example.movies.common.*
import com.example.movies.domain.model.MoviesList
import com.example.movies.ui.detailed.DetailedActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), MoviesAdapter.OnItemClickListener {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesAdapter = MoviesAdapter(this)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.movies.observe(this, moviesObserver)
        mainViewModel.currentSearch.observe(this, searchObserver)

        movies_rv.adapter = moviesAdapter
        movies_rv.layoutManager = GridLayoutManager(this, 2)
        movies_rv.addOnScrollListener(mainViewModel.onScrollListener)
        edtxt.addTextChangedListener(mainViewModel.onTextChangedListener)

        mainViewModel.showMovieList(1)
    }

    private val searchObserver = Observer<String> {
        moviesAdapter.clear()

        with(mainViewModel) {
            pagesLoaded = 1
            rvState = if (it.isNotEmpty()) {
                searchMovies(it, pagesLoaded)
                RVState.SEARCH
            } else {
                showMovieList(pagesLoaded)
                RVState.TRENDING
            }
        }
    }

    private val moviesObserver = Observer<Result<MoviesList>> { result ->
        if (result.status == Status.SUCCESS) {
            result.data?.let { moviesList ->
                moviesAdapter.setupMovies(moviesList.results)
                moviesList.results.forEachIndexed { index, it ->
                    if (!it.poster_path.isNullOrEmpty()) {
                        mainViewModel.getImage(
                            it.poster_path,
                            ImageSizes.XLARGE,
                            onSuccess = { r ->
                                mainViewModel.viewModelScope.launch(Dispatchers.Main) {
                                    val bitmap = BitmapFactory.decodeStream(r.data)
                                    if (r.status == Status.SUCCESS) it.image = bitmap
                                    moviesAdapter.insertMovie(
                                        it,
                                        20 * (mainViewModel.pagesLoaded - 1) + index
                                    )
                                }
                            },
                            onError = { e -> Log.e("Server error", "", e) }
                        )
                    } else {
                        it.image = BitmapFactory.decodeResource(
                            resources,
                            R.drawable.ic_no_poster
                        )
                        moviesAdapter.insertMovie(
                            it,
                            20 * (mainViewModel.pagesLoaded - 1) + index
                        )
                    }
                }
            }
        }
    }

    override fun onClickListener(id: Int) {
        val intent = Intent(this, DetailedActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}
