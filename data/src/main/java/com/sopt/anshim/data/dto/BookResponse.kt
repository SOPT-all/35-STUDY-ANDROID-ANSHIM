package com.sopt.anshim.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class BookResponse(
    val display: Int,
    val item: List<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)