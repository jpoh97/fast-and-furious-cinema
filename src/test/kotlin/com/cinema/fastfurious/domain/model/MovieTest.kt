package com.cinema.fastfurious.domain.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime
import java.util.Collections

@DisplayName("Person domain tests")
class MovieTest {

    @Test
    fun `2 movies with the same data must be equal`() {
        val currentTime = LocalDateTime.now().plusDays(1)
        val shows = Collections.singletonList(Show(time = currentTime, price = 20.0))
        val reviews = Collections.singletonList(ReviewRating(5f))
        val movie1 = Movie(id = 1, shows, reviews)
        val movie2 = Movie(id = 1, shows, reviews)

        assertEquals(movie1, movie2)
    }

    @Test
    fun `2 movies with the different data must be not equal`() {
        val tomorrowTime = LocalDateTime.now().plusDays(1)
        val shows = Collections.singletonList(Show(time = tomorrowTime, price = 20.0))
        val reviews = Collections.singletonList(ReviewRating(5f))
        val movie1 = Movie(id = 1, shows, reviews)
        val movie2 = Movie(id = 2, shows, reviews)

        assertNotEquals(movie1, movie2)
    }

    @Test
    fun `showtime in the past should throws IllegalArgumentException`() {
        val yesterdayTime = LocalDateTime.now().minusDays(1)
        val invalidShowtime = assertThrows<IllegalArgumentException> {
            Show(time = yesterdayTime, price = 20.0)
        }

        assertEquals("The showtime can not be in the past", invalidShowtime.message)
    }
}