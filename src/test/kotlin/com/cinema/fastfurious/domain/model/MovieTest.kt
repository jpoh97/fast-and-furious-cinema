package com.cinema.fastfurious.domain.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDateTime
import java.util.Collections

@DisplayName("Movie domain tests")
class MovieTest {

    @Test
    fun `2 movies with the same data must be equal`() {
        val currentTime = LocalDateTime.now().plusDays(1)
        val shows = Collections.singletonList(Show(id = 1, movieId = 1, time = currentTime, price = 42.0))
        val reviews = Collections.singletonList(ReviewRating(id = 1, movieId = 5, value = 5f))
        val movie1 = Movie(id = 1, imdbID = "tt0232500", shows, reviews)
        val movie2 = Movie(id = 1, imdbID = "tt0232500", shows, reviews)

        assertEquals(movie1, movie2)
    }

    @Test
    fun `2 movies with the different data must be not equal`() {
        val tomorrowTime = LocalDateTime.now().plusDays(1)
        val shows = Collections.singletonList(Show(id = 1, movieId = 1, time = tomorrowTime, price = 42.0))
        val reviews = Collections.singletonList(ReviewRating(id = 1, movieId = 5, value = 5f))
        val movie1 = Movie(id = 1, imdbID = "tt0232500", shows, reviews)
        val movie2 = Movie(id = 2, imdbID = "tt0322259", shows, reviews)

        assertNotEquals(movie1, movie2)
    }

    @Test
    fun `showtime in the past should throws IllegalArgumentException`() {
        val invalidShowtime = assertThrows<IllegalArgumentException> {
            Show(id = 1, movieId = 1, time = LocalDateTime.now().minusDays(1), price = 42.0)
        }

        assertEquals("The showtime can not be in the past", invalidShowtime.message)
    }

    @Test
    fun `showtime with negative price should throws IllegalArgumentException`() {
        val invalidShowtime = assertThrows<IllegalArgumentException> {
            Show(id = 1, movieId = 1, time = LocalDateTime.now().plusDays(1), price = -42.0)
        }

        assertEquals("The price must be greater or equals to zero", invalidShowtime.message)
    }

    @ParameterizedTest
    @ValueSource(floats = [0.9f, 5.1f, 0f, -3f])
    fun `review rating with invalid value should throws IllegalArgumentException`(rating: Float) {
        val invalidShowtime = assertThrows<IllegalArgumentException> {
            ReviewRating(id = 1, movieId = 5, value = rating)
        }

        assertEquals("The review rating must be between 1 and 5 starts", invalidShowtime.message)
    }
}