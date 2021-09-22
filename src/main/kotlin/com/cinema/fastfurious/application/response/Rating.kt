package com.cinema.fastfurious.application.response

import com.fasterxml.jackson.annotation.JsonFormat

@JsonFormat(with = [JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES])
data class Rating(val source: String, val value: String)
