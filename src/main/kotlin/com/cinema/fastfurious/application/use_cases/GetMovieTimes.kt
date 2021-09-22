package com.cinema.fastfurious.application.use_cases

import com.cinema.fastfurious.application.response.ShowTime
import com.cinema.fastfurious.domain.MovieRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class GetMovieTimes(private val movieRepository: MovieRepository) {

    fun execute(): Flux<ShowTime> =
        movieRepository.findAllShows().map { ShowTime(movieId = it.movieId, time = it.time) }
}