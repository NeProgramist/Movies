package com.example.movies.ui.detailed

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import com.example.movies.R
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import com.example.movies.common.ImageSizes
import com.example.movies.domain.model.DetailedMovie
import com.example.movies.common.Result
import com.example.movies.common.Status
import kotlinx.android.synthetic.main.activity_detailed.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailedActivity : AppCompatActivity() {

    private var id = -1
    private lateinit var detailedViewModel: DetailedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        id = intent.getIntExtra("id", -1)
        detailedViewModel = ViewModelProvider(this).get(DetailedViewModel::class.java)
        detailedViewModel.movie.observe(this, movieObserver)

        back_button.setOnClickListener{
            finish()
        }

        detailedViewModel.showMovie(id)
    }

    private val movieObserver = Observer<Result<DetailedMovie>> { result ->
        if (result.status == Status.SUCCESS) {
            result.data?.let {
                if (!it.poster_path.isNullOrEmpty()) {
                    detailedViewModel.getImage(
                        it.poster_path,
                        ImageSizes.ORIGINAL,
                        onSuccess = { r ->
                            val bitmap = BitmapFactory.decodeStream(r.data)
                            detailedViewModel.viewModelScope.launch(Dispatchers.Main) {
                                Log.d("asdfasdf", r.status.toString())
                                if (r.status == Status.SUCCESS) detailed_img.setImageBitmap(bitmap)

                                detailed_title.text = it.title
                                detailed_description.text = it.overview

                                detailed_release_date.text = "Release date: ${it.release_date}"

                                detailed_cast_title.text = "Cast:"
                                detailed_cast.text = it.credits.cast.fold("") { acc, cast ->
                                    "$acc, ${cast.name}"
                                }.drop(2)

                                detailed_directors_title.text = "Directors:"
                                detailed_directors.text = it
                                    .credits
                                    .crew
                                    .filter { item -> item.job.contains("Director") }
                                    .fold("") { acc, crew -> "$acc, ${crew.name}" }
                                    .drop(2)

                                detailed_genres_title.text = "Genres:"
                                detailed_genres.text = it.genres.fold("") { acc, genre ->
                                    "$acc, ${genre.name}"
                                }.drop(2)
                            }
                        },
                        onError = { e -> Log.e("Server error", "", e) }
                    )
                }
            }
        }
    }

}
