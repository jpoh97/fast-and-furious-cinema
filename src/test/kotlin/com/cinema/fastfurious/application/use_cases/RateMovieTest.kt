package com.cinema.fastfurious.application.use_cases

import com.cinema.fastfurious.domain.MovieRepository
import com.cinema.fastfurious.domain.model.ReviewRating
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

@DisplayName("Rate movie use case tests")
class RateMovieTest {

    @Test
    fun `rate movie with perfect score successfully`() {
        val movieRepository = mockk<MovieRepository>()
        val rateMovie = RateMovie(movieRepository)
        val reviewRating = ReviewRating(id = 1, movieId = 42, value = 5f)

        every { movieRepository.rateMovie(reviewRating = reviewRating) } returns Mono.empty()

        val ratedMovie = rateMovie.execute(movieId = 42, reviewRating = reviewRating)

        StepVerifier.create(ratedMovie).verifyComplete()
    }

    @Test
    fun `rate movie with lower score successfully`() {
        val movieRepository = mockk<MovieRepository>()
        val rateMovie = RateMovie(movieRepository)
        val reviewRating = ReviewRating(id = 1, movieId = 42, value = 1f)

        every { movieRepository.rateMovie(reviewRating = reviewRating) } returns Mono.empty()

        val ratedMovie = rateMovie.execute(movieId = 42, reviewRating = reviewRating)

        StepVerifier.create(ratedMovie).verifyComplete()
    }
}