package com.cinema.fastfurious.application

import com.cinema.fastfurious.application.response.ShowTime
import com.cinema.fastfurious.domain.MovieRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class GetMovieTimes(private val movieRepository: MovieRepository) {

    fun execute(): Flux<ShowTime> {
        return movieRepository.findAllShows()
            .map { ShowTime(movieId = it.movieId, time = it.time) }
    }
}