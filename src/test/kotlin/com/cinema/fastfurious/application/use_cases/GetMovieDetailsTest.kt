package com.cinema.fastfurious.application.use_cases

import com.cinema.fastfurious.application.MovieDetailsRepository
import com.cinema.fastfurious.application.response.MovieDetail
import com.cinema.fastfurious.domain.MovieRepository
import com.cinema.fastfurious.domain.model.Movie
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.time.LocalDate
import java.time.Month
import java.util.*

@DisplayName("Get movie details use case tests")
class GetMovieDetailsTest {

    @Test
    fun `get movie details successfully`() {
        val movieRepository = mockk<MovieRepository>()
        val movieDetailsRepository = mockk<MovieDetailsRepository>()
        val getMovieDetails = GetMovieDetails(movieRepository, movieDetailsRepository)
        val movieDetail = MovieDetail(
            imdbID = "tt0232500",
            title = "The Fast and the Furious",
            genre = "Action, Crime, Thriller",
            released = LocalDate.of(2001, Month.JUNE, 22),
            imdbRating = 6.8f,
            runtime = "106 min",
            ratings = Collections.emptyList()
        )

        every { movieRepository.findById(movieId = 42) } returns Mono.just(Movie(id = 42, imdbID = "tt0232500"))
        every { movieDetailsRepository.getDetails(imdbID = "tt0232500") } returns Mono.just(movieDetail)

        val movieDetails = getMovieDetails.execute(movieId = 42)

        StepVerifier.create(movieDetails).expectNext(movieDetail).verifyComplete()
    }
}