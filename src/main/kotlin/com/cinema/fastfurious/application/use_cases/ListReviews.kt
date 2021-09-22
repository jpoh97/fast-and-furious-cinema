package com.cinema.fastfurious.application.use_cases

import com.cinema.fastfurious.domain.MovieRepository
import com.cinema.fastfurious.domain.model.ReviewRating
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class ListReviews(private val movieRepository: MovieRepository) {

    fun execute(movieId: Int): Flux<ReviewRating> = movieRepository.listReviewsByMovie(movieId)
}