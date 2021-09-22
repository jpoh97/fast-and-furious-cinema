package com.cinema.fastfurious.application.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

@JsonFormat(with = [JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES])
data class MovieDetail(
    val imdbID: String,
    val title: String,
    val genre: String,
    @JsonFormat(pattern = "dd MMM yyyy") val released: LocalDate,
    val imdbRating: Float,
    val runtime: String,
    val ratings: List<Rating>
)