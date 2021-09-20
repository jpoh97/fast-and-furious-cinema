package com.cinema.fastfurious.infraestructure.repository

import com.cinema.fastfurious.domain.MovieRepository
import com.cinema.fastfurious.domain.model.Show
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Repository
class MovieRepositoryImpl(
    private val movieRepositoryR2DBC: MovieRepositoryR2DBC,
    private val showRepositoryR2DBC: ShowRepositoryR2DBC
) : MovieRepository {

    override fun exists(movieId: Int): Mono<Boolean> {
        return movieRepositoryR2DBC.existsById(movieId)
    }

    @Transactional
    override fun replaceShows(movieId: Int, shows: List<Show>): Mono<Void> {
        return showRepositoryR2DBC.deleteAllByMovieId(movieId)
            .flatMap { showRepositoryR2DBC.saveAll(shows).then() }
    }
}