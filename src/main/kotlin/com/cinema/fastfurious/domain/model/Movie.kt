package com.cinema.fastfurious.domain.model

import org.springframework.data.annotation.Id
import java.util.Collections

data class Movie(
    @Id val id: Int,
    val imdbID: String,
    val shows: List<Show> = Collections.emptyList(),
    val reviewRatings: List<ReviewRating> = Collections.emptyList()
)