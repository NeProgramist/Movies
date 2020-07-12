package com.example.movies.ui.detailed

import android.os.Bundle
import com.example.movies.R
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.domain.model.DetailedMovie
import com.example.movies.common.Result
import com.example.movies.common.Status

class DetailedActivity : AppCompatActivity() {

    private var id = -1
    private lateinit var detailedViewModel: DetailedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        id = intent.getIntExtra("id", -1)
        detailedViewModel = ViewModelProvider(this).get(DetailedViewModel::class.java)

        detailedViewModel.movie.observe(this, movieObserver)
        detailedViewModel.showMovie(id)
    }

    private val movieObserver = Observer<Result<DetailedMovie>> { result ->
        if (result.status == Status.SUCCESS) {

        } else {

        }
    }
}
