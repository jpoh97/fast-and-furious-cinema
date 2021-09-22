package com.cinema.fastfurious.infraestructure.repository

import com.cinema.fastfurious.application.MovieDetailsRepository
import com.cinema.fastfurious.application.response.MovieDetail
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

@Repository
class MovieDetailsRepositoryIMDb(private val webClient: WebClient): MovieDetailsRepository {

    @Value("\${imdb.apiKey}")
    private lateinit var apiKey: String

    override fun getDetails(imdbID: String): Mono<MovieDetail> {
        return webClient.get()
            .uri("http://www.omdbapi.com/?apiKey=$apiKey&i=$imdbID")
            .retrieve()
            .bodyToMono()
    }
}