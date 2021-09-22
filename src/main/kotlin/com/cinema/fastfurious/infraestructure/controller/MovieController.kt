package com.cinema.fastfurious.infraestructure.controller

import com.cinema.fastfurious.application.response.MovieDetail
import com.cinema.fastfurious.application.use_cases.GetMovieDetails
import com.cinema.fastfurious.application.use_cases.GetMovieTimes
import com.cinema.fastfurious.application.use_cases.UpdateMovieShows
import com.cinema.fastfurious.application.response.ShowTime
import com.cinema.fastfurious.domain.model.Show
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/movie")
class MovieController(
    private val updateMovieShows: UpdateMovieShows,
    private val getMovieTimes: GetMovieTimes,
    private val getMovieDetails: GetMovieDetails
) {

    @PostMapping("/{movieId}/shows")
    fun saveShows(@PathVariable("movieId") movieId: Int, @RequestBody shows: List<Show>): Mono<Void> =
        updateMovieShows.execute(movieId, shows)


    @GetMapping("/shows/time")
    fun getShowTimes(): Flux<ShowTime> = getMovieTimes.execute()


    @GetMapping("/{movieId}")
    fun getMovieDetails(@PathVariable("movieId") movieId: Int): Mono<MovieDetail> = getMovieDetails.execute(movieId)

}