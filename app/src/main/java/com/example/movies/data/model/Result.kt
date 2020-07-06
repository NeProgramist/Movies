package com.example.movies.data.model

data class Result(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)