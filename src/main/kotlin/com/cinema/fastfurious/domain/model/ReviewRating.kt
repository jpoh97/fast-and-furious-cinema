package com.cinema.fastfurious.domain.model

import org.springframework.data.annotation.Id

data class ReviewRating(@Id val id: Int, val value: Float) {
    init {
        require(value in 1.0..5.0) { "The review rating must be between 1 and 5 starts" }
    }
}