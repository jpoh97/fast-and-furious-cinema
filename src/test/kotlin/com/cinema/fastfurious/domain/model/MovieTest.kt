package com.cinema.fastfurious.domain.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*

@DisplayName("Person domain tests")
class MovieTest {

    @Test
    @DisplayName("2 movies with the same data must be equal")
    fun testTwoMoviesWithEqualData() {
        val currentTime = LocalDateTime.now().plusDays(1)
        val shows = Collections.singletonList(Show(time = currentTime, price = 20.0))
        val reviews = Collections.singletonList(ReviewRating(5f))
        val movie1 = Movie(id = 1, shows, reviews)
        val movie2 = Movie(id = 1, shows, reviews)

        assertEquals(movie1, movie2)
    }

    @Test
    @DisplayName("2 movies with the different data must be not equal")
    fun testTwoMoviesWithNotEqualData() {
        val currentTime = LocalDateTime.now().plusDays(1)
        val shows = Collections.singletonList(Show(time = currentTime, price = 20.0))
        val reviews = Collections.singletonList(ReviewRating(5f))
        val movie1 = Movie(id = 1, shows, reviews)
        val movie2 = Movie(id = 2, shows, reviews)

        assertEquals(movie1, movie2)
    }

}