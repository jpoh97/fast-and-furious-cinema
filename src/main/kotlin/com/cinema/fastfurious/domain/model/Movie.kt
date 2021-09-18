package com.cinema.fastfurious.domain.model

data class Movie(
    val id: Int,
    val imdbID: String,
    val shows: List<Show>,
    val reviewRatings: List<ReviewRating>
)