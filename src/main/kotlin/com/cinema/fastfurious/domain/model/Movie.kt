package com.cinema.fastfurious.domain.model

import org.springframework.data.annotation.Id

data class Movie(
    @Id val id: Int,
    val imdbID: String,
    val shows: List<Show>,
    val reviewRatings: List<ReviewRating>
)