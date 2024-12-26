package com.sopt.remote

import kotlinx.serialization.Serializable

@Serializable
data class BookPaginationDto(
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<BookDto>,
)

@Serializable
data class BookDto(
    val title: String,
    val image: String,
    val author: String,
    val discount: String,
    val publisher: String,
    val isbn: String,
    val description: String,
)
