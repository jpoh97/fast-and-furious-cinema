package com.cinema.fastfurious.application.use_cases

import com.cinema.fastfurious.application.response.ShowTime
import com.cinema.fastfurious.domain.MovieRepository
import com.cinema.fastfurious.domain.model.Show
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import java.time.LocalDateTime
import java.util.stream.Stream

@DisplayName("Get movie times use case tests")
class GetMovieTimesTest {

    @Test
    fun `get two movie times successfully`() {
        val movieRepository = mockk<MovieRepository>()
        val getMovieTimes = GetMovieTimes(movieRepository)

        val show1 = Show(id = 1, movieId = 42, LocalDateTime.now().plusDays(1), 42.0)
        val show2 = Show(id = 2, movieId = 42, LocalDateTime.now().plusDays(3), 7.0)

        every { movieRepository.findAllShows() } returns Flux.fromStream(Stream.of(show1, show2))

        val movieTimes = getMovieTimes.execute()

        val showTime1 = ShowTime(show1.movieId, show1.time)
        val showTime2 = ShowTime(show2.movieId, show2.time)
        StepVerifier.create(movieTimes).expectNext(showTime1).expectNext(showTime2).verifyComplete()
    }

    @Test
    fun `get empty movie times list successfully`() {
        val movieRepository = mockk<MovieRepository>()
        val getMovieTimes = GetMovieTimes(movieRepository)

        every { movieRepository.findAllShows() } returns Flux.fromStream(Stream.empty())

        val movieTimes = getMovieTimes.execute()

        StepVerifier.create(movieTimes).expectNextCount(0).verifyComplete()
    }
}