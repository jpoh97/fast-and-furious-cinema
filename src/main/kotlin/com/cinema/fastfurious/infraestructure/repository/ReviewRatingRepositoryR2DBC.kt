package com.cinema.fastfurious.infraestructure.repository

import com.cinema.fastfurious.domain.model.ReviewRating
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface ReviewRatingRepositoryR2DBC : ReactiveCrudRepository<ReviewRating, Int> {

    fun findAllByMovieId(movieId: Int): Flux<ReviewRating>

}