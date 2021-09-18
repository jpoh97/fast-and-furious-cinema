package com.cinema.fastfurious.domain.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*

class MovieTest {

    @Test
    fun testTwoMoviesWithEqualData() {
        val currentTime = LocalDateTime.now().plusDays(1)
        val shows = Collections.singletonList(Show(time = currentTime, price = 20.0))
        val reviews = Collections.singletonList(ReviewRating(5f))
        val movie1 = Movie(id = 1, shows, reviews)
        val movie2 = Movie(id = 1, shows, reviews)

        assertEquals(movie1, movie2)
    }
}