package com.cinema.fastfurious.domain.model

import org.springframework.data.annotation.Id
import java.time.LocalDateTime

data class Show(@Id var id: Int, var movieId: Int, var time: LocalDateTime, var price: Double) {

    init {
        require(LocalDateTime.now().isBefore(time)) { "The showtime can not be in the past" }
        require(price >= 0) { "The price must be greater or equals to zero" }
    }
}