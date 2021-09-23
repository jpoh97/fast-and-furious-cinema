package com.cinema.fastfurious.application.use_cases

import com.cinema.fastfurious.domain.MovieRepository
import com.cinema.fastfurious.domain.model.Show
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.time.LocalDateTime
import java.util.*

@DisplayName("Update movie show times use case tests")
class UpdateMovieShowsTest {

    @Test
    fun `update movie show times successfully`() {
        val movieRepository = mockk<MovieRepository>()
        val updateMovieShows = UpdateMovieShows(movieRepository)

        val shows = Collections.singletonList(Show(id = 1, movieId = 42, LocalDateTime.now().plusDays(1), 42.0))

        every { movieRepository.exists(movieId = 42) } returns Mono.just(true)
        every { movieRepository.replaceShows(movieId = 42, shows = shows) } returns Mono.empty()

        val replacedMovies = updateMovieShows.execute(movieId = 42, shows = shows)

        StepVerifier.create(replacedMovies).verifyComplete()
    }

    @Test
    fun `update movie show times no existing movie throws error`() {
        val movieRepository = mockk<MovieRepository>()
        val updateMovieShows = UpdateMovieShows(movieRepository)

        every { movieRepository.exists(movieId = 42) } returns Mono.just(false)

        val replacedMovies = updateMovieShows.execute(movieId = 42, shows = Collections.emptyList())

        StepVerifier.create(replacedMovies).expectErrorMessage("The movie does not exist").verify()
    }
}