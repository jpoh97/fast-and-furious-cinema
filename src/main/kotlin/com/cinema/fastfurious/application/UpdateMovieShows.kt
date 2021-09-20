package com.cinema.fastfurious.application

import com.cinema.fastfurious.domain.DomainException
import com.cinema.fastfurious.domain.MovieRepository
import com.cinema.fastfurious.domain.model.Show
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UpdateMovieShows(private val movieRepository: MovieRepository) {

    fun execute(movieId: Int, shows: List<Show>): Mono<Void> {
        return movieRepository.exists(movieId)
            .filter { it == true }
            .switchIfEmpty(Mono.error(DomainException("The movie does not exist")))
            .flatMap {
                shows.forEach { show -> show.movieId = movieId }
                movieRepository.replaceShows(movieId, shows)
            }
    }

}