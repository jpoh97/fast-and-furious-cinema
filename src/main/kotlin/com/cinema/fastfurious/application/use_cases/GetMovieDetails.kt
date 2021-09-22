package com.cinema.fastfurious.application.use_cases

import com.cinema.fastfurious.application.MovieDetailsRepository
import com.cinema.fastfurious.application.response.MovieDetail
import com.cinema.fastfurious.domain.MovieRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class GetMovieDetails(
    private val movieRepository: MovieRepository,
    private val movieDetailsRepository: MovieDetailsRepository
) {

    fun execute(movieId: Int): Mono<MovieDetail> = movieRepository.findById(movieId).flatMap { movieDetailsRepository.getDetails(it.imdbID) }

}