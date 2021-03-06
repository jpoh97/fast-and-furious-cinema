package com.cinema.fastfurious.domain

import com.cinema.fastfurious.domain.model.Movie
import com.cinema.fastfurious.domain.model.ReviewRating
import com.cinema.fastfurious.domain.model.Show
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface MovieRepository {

    fun exists(movieId: Int): Mono<Boolean>

    fun replaceShows(movieId: Int, shows: List<Show>): Mono<Void>

    fun findAllShows(): Flux<Show>

    fun findById(movieId: Int): Mono<Movie>

    fun rateMovie(reviewRating: ReviewRating): Mono<Void>

    fun listReviewsByMovie(movieId: Int): Flux<ReviewRating>

}