package com.cinema.fastfurious.infraestructure.repository

import com.cinema.fastfurious.domain.model.Movie
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface MovieRepositoryR2DBC : ReactiveCrudRepository<Movie, Int>