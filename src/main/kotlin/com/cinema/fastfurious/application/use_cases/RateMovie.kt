package com.cinema.fastfurious.application.use_cases

import com.cinema.fastfurious.domain.MovieRepository
import com.cinema.fastfurious.domain.model.ReviewRating
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class RateMovie(private val movieRepository: MovieRepository) {

    fun execute(movieId: Int, reviewRating: ReviewRating): Mono<Void> {
        reviewRating.movieId = movieId
        return movieRepository.rateMovie(reviewRating)
    }
}