package com.sopt.anshim.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class BookResponse(
    val display: Int,
    val items: List<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)