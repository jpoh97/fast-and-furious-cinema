package com.cinema.fastfurious.infraestructure

import java.time.LocalDate

data class MovieDetail(
    val imdbID: String,
    val name: String,
    val description: String,
    val releaseDate: LocalDate,
    val imdbRating: Float,
    val runtime: String,
    val ratings: List<Rating>
)