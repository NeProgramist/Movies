package com.example.movies.ui.main

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.core.view.children
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.common.ImageSizes
import com.example.movies.common.*
import com.example.movies.domain.model.MoviesList
import com.example.movies.ui.detailed.DetailedActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity(), MoviesAdapter.OnItemClickListener {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesAdapter = MoviesAdapter(this)
        movies_rv.adapter = moviesAdapter
        movies_rv.layoutManager = GridLayoutManager(this, 2)
        val onScrollListener = object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastId = recyclerView.children.last().id
                if (lastId >= mainViewModel.pagesLoaded*15) {
                    mainViewModel.pagesLoaded = 2
                    mainViewModel.showMovieList()
                }
            }
        }
        movies_rv.addOnScrollListener(onScrollListener)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.movies.observe(this, moviesObserver)

        edtxt.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) mainViewModel.searchMovies(s.toString())
                else mainViewModel.showMovieList()
            }
        })

        mainViewModel.showMovieList()
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
                                        moviesAdapter.insertMovie(it, index)
                                    }
                                },
                                onError = { e -> Log.e("Server error", "", e) }
                            )
                        } else {
//                        it.image =
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
