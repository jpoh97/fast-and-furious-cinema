package com.cinema.fastfurious.application

import com.cinema.fastfurious.application.response.MovieDetail
import reactor.core.publisher.Mono

interface MovieDetailsRepository {

    fun getDetails(imdbID: String): Mono<MovieDetail>

}