package com.cinema.fastfurious.application.use_cases

import com.cinema.fastfurious.domain.MovieRepository
import com.cinema.fastfurious.domain.model.ReviewRating
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import java.util.stream.Stream

@DisplayName("List movie review use case tests")
class ListReviewsTest {

    @Test
    fun `list movie reviews successfully`() {
        val movieRepository = mockk<MovieRepository>()
        val listReviewsByMovie = ListReviews(movieRepository)
        val reviewRating1 = ReviewRating(id = 1, movieId = 42, value = 3f)

        every { movieRepository.listReviewsByMovie(movieId = 42) } returns Flux.fromStream(Stream.of(reviewRating1))

        val listedMovies = listReviewsByMovie.execute(movieId = 42)

        StepVerifier.create(listedMovies).expectNext(reviewRating1).verifyComplete()
    }


    @Test
    fun `list movie reviews empty list successfully`() {
        val movieRepository = mockk<MovieRepository>()
        val listReviewsByMovie = ListReviews(movieRepository)

        every { movieRepository.listReviewsByMovie(movieId = 42) } returns Flux.fromStream(Stream.empty())

        val listedMovies = listReviewsByMovie.execute(movieId = 42)

        StepVerifier.create(listedMovies).verifyComplete()
    }
}