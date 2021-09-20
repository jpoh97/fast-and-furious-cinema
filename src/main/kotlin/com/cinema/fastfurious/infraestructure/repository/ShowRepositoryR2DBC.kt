package com.cinema.fastfurious.infraestructure.repository

import com.cinema.fastfurious.domain.model.Show
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface ShowRepositoryR2DBC: ReactiveCrudRepository<Show, Int> {

    fun deleteAllByMovieId(movieId: Int): Mono<Void>

}