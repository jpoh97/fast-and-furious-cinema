package com.cinema.fastfurious.infraestructure.repository

import com.cinema.fastfurious.domain.MovieRepository
import com.cinema.fastfurious.domain.model.Movie
import com.cinema.fastfurious.domain.model.ReviewRating
import com.cinema.fastfurious.domain.model.Show
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class MovieRepositoryH2(
    private val movieRepositoryR2DBC: MovieRepositoryR2DBC,
    private val showRepositoryR2DBC: ShowRepositoryR2DBC,
    private val reviewRatingRepositoryR2DBC: ReviewRatingRepositoryR2DBC
) : MovieRepository {

    override fun exists(movieId: Int): Mono<Boolean> = movieRepositoryR2DBC.existsById(movieId)

    override fun replaceShows(movieId: Int, shows: List<Show>): Mono<Void> =
        showRepositoryR2DBC.deleteAllByMovieId(movieId).flatMap { showRepositoryR2DBC.saveAll(shows).then() }

    override fun findAllShows(): Flux<Show> = showRepositoryR2DBC.findAll()

    override fun findById(movieId: Int): Mono<Movie> = movieRepositoryR2DBC.findById(movieId)

    override fun rateMovie(reviewRating: ReviewRating): Mono<Void> =
        reviewRatingRepositoryR2DBC.save(reviewRating).then()

    override fun listReviewsByMovie(movieId: Int): Flux<ReviewRating> =
        reviewRatingRepositoryR2DBC.findAllByMovieId(movieId)

}