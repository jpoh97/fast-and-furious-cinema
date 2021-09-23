package com.cinema.fastfurious.infraestructure.repository

import com.cinema.fastfurious.domain.model.Movie
import com.cinema.fastfurious.domain.model.ReviewRating
import com.cinema.fastfurious.domain.model.Show
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.time.LocalDateTime
import java.util.*
import java.util.stream.Stream

@DisplayName("Movie repository implementation tests")
class MovieRepositoryImplTest {

    @Test
    fun `exists return true`() {
        val movieRepositoryR2DBC = mockk<MovieRepositoryR2DBC>()
        val showRepositoryR2DBC = mockk<ShowRepositoryR2DBC>()
        val reviewRatingRepositoryR2DBC = mockk<ReviewRatingRepositoryR2DBC>()
        val movieRepository = MovieRepositoryImpl(movieRepositoryR2DBC, showRepositoryR2DBC, reviewRatingRepositoryR2DBC)
        val movieId: Int = 42

        every { movieRepositoryR2DBC.existsById(movieId) } returns Mono.just(true)

        val exists = movieRepository.exists(movieId = 42)

        StepVerifier.create(exists).expectNext(true).verifyComplete()
    }

    @Test
    fun `exists return false`() {
        val movieRepositoryR2DBC = mockk<MovieRepositoryR2DBC>()
        val showRepositoryR2DBC = mockk<ShowRepositoryR2DBC>()
        val reviewRatingRepositoryR2DBC = mockk<ReviewRatingRepositoryR2DBC>()
        val movieRepository = MovieRepositoryImpl(movieRepositoryR2DBC, showRepositoryR2DBC, reviewRatingRepositoryR2DBC)
        val movieId: Int = 42

        every { movieRepositoryR2DBC.existsById(movieId) } returns Mono.just(false)

        val exists = movieRepository.exists(movieId = 42)

        StepVerifier.create(exists).expectNext(false).verifyComplete()
    }

    @Test
    fun `replace shows successfully`() {
        val movieRepositoryR2DBC = mockk<MovieRepositoryR2DBC>()
        val showRepositoryR2DBC = mockk<ShowRepositoryR2DBC>()
        val reviewRatingRepositoryR2DBC = mockk<ReviewRatingRepositoryR2DBC>()
        val movieRepository = MovieRepositoryImpl(movieRepositoryR2DBC, showRepositoryR2DBC, reviewRatingRepositoryR2DBC)
        val shows = Collections.singletonList(Show(id = 1, movieId = 42, time = LocalDateTime.now().plusDays(1), price = 7.0))

        every { showRepositoryR2DBC.deleteAllByMovieId(movieId = 42) } returns Mono.just(true)
        every { showRepositoryR2DBC.saveAll(shows) } returns Flux.fromIterable(shows)

        val replacedMovies = movieRepository.replaceShows(movieId = 42, shows)

        StepVerifier.create(replacedMovies).verifyComplete()
    }

    @Test
    fun `find all movies`() {
        val movieRepositoryR2DBC = mockk<MovieRepositoryR2DBC>()
        val showRepositoryR2DBC = mockk<ShowRepositoryR2DBC>()
        val reviewRatingRepositoryR2DBC = mockk<ReviewRatingRepositoryR2DBC>()
        val movieRepository = MovieRepositoryImpl(movieRepositoryR2DBC, showRepositoryR2DBC, reviewRatingRepositoryR2DBC)
        val show = Show(id = 1, movieId = 42, time = LocalDateTime.now().plusDays(1), price = 7.0)

        every { showRepositoryR2DBC.findAll() } returns Flux.fromStream(Stream.of(show))

        val movies = movieRepository.findAllShows()

        StepVerifier.create(movies).expectNext(show).verifyComplete()
    }

    @Test
    fun `find all movies empty response`() {
        val movieRepositoryR2DBC = mockk<MovieRepositoryR2DBC>()
        val showRepositoryR2DBC = mockk<ShowRepositoryR2DBC>()
        val reviewRatingRepositoryR2DBC = mockk<ReviewRatingRepositoryR2DBC>()
        val movieRepository = MovieRepositoryImpl(movieRepositoryR2DBC, showRepositoryR2DBC, reviewRatingRepositoryR2DBC)

        every { showRepositoryR2DBC.findAll() } returns Flux.empty()

        val movies = movieRepository.findAllShows()

        StepVerifier.create(movies).expectNextCount(0).verifyComplete()
    }

    @Test
    fun `find movie by id`() {
        val movieRepositoryR2DBC = mockk<MovieRepositoryR2DBC>()
        val showRepositoryR2DBC = mockk<ShowRepositoryR2DBC>()
        val reviewRatingRepositoryR2DBC = mockk<ReviewRatingRepositoryR2DBC>()
        val movieRepository = MovieRepositoryImpl(movieRepositoryR2DBC, showRepositoryR2DBC, reviewRatingRepositoryR2DBC)
        val movie = Movie(id = 42, imdbID = "tt0232500")

        every { movieRepositoryR2DBC.findById(42) } returns Mono.just(movie)

        val foundMovie = movieRepository.findById(movieId = 42)

        StepVerifier.create(foundMovie).expectNext(movie).verifyComplete()
    }

    @Test
    fun `find movie by id empty response`() {
        val movieRepositoryR2DBC = mockk<MovieRepositoryR2DBC>()
        val showRepositoryR2DBC = mockk<ShowRepositoryR2DBC>()
        val reviewRatingRepositoryR2DBC = mockk<ReviewRatingRepositoryR2DBC>()
        val movieRepository = MovieRepositoryImpl(movieRepositoryR2DBC, showRepositoryR2DBC, reviewRatingRepositoryR2DBC)

        every { movieRepositoryR2DBC.findById(42) } returns Mono.empty()

        val foundMovie = movieRepository.findById(movieId = 42)

        StepVerifier.create(foundMovie).expectNextCount(0).verifyComplete()
    }

    @Test
    fun `rate movie successfully`() {
        val movieRepositoryR2DBC = mockk<MovieRepositoryR2DBC>()
        val showRepositoryR2DBC = mockk<ShowRepositoryR2DBC>()
        val reviewRatingRepositoryR2DBC = mockk<ReviewRatingRepositoryR2DBC>()
        val movieRepository = MovieRepositoryImpl(movieRepositoryR2DBC, showRepositoryR2DBC, reviewRatingRepositoryR2DBC)
        val reviewRating = ReviewRating(id = 1, movieId = 42, value = 3f)

        every { reviewRatingRepositoryR2DBC.save(reviewRating) } returns Mono.just(reviewRating)

        val foundMovie = movieRepository.rateMovie(reviewRating = reviewRating)

        StepVerifier.create(foundMovie).verifyComplete()
    }

    @Test
    fun `list reviews by movie return one`() {
        val movieRepositoryR2DBC = mockk<MovieRepositoryR2DBC>()
        val showRepositoryR2DBC = mockk<ShowRepositoryR2DBC>()
        val reviewRatingRepositoryR2DBC = mockk<ReviewRatingRepositoryR2DBC>()
        val movieRepository = MovieRepositoryImpl(movieRepositoryR2DBC, showRepositoryR2DBC, reviewRatingRepositoryR2DBC)
        val reviewRating = ReviewRating(id = 1, movieId = 42, value = 3f)

        every { reviewRatingRepositoryR2DBC.findAllByMovieId(movieId = 42) } returns Flux.fromIterable(Collections.singletonList(reviewRating))

        val foundMovie = movieRepository.listReviewsByMovie(movieId = 42)

        StepVerifier.create(foundMovie).expectNext(reviewRating).verifyComplete()
    }

    @Test
    fun `list reviews by movie return empty`() {
        val movieRepositoryR2DBC = mockk<MovieRepositoryR2DBC>()
        val showRepositoryR2DBC = mockk<ShowRepositoryR2DBC>()
        val reviewRatingRepositoryR2DBC = mockk<ReviewRatingRepositoryR2DBC>()
        val movieRepository = MovieRepositoryImpl(movieRepositoryR2DBC, showRepositoryR2DBC, reviewRatingRepositoryR2DBC)

        every { reviewRatingRepositoryR2DBC.findAllByMovieId(movieId = 42) } returns Flux.empty()

        val foundMovie = movieRepository.listReviewsByMovie(movieId = 42)

        StepVerifier.create(foundMovie).verifyComplete()
    }
}