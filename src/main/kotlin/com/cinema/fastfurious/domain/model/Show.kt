package com.cinema.fastfurious.domain.model

import java.time.LocalDateTime

data class Show(val time: LocalDateTime, val price: Double) {

    init {
       require(LocalDateTime.now().isBefore(time)) { "The showtime can not be in the past" }
       require(price >= 0) { "The price must be greater or equals to zero" }
    }
}