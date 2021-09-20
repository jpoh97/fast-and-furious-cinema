package com.cinema.fastfurious.domain

import com.cinema.fastfurious.domain.model.Show
import reactor.core.publisher.Mono

interface MovieRepository {

    fun exists(movieId: Int): Mono<Boolean>

    fun replaceShows(movieId: Int, shows: List<Show>): Mono<Void>

}