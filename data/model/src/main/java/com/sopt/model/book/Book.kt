package com.sopt.model.book

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val title: String,
    val author: String,
    val price: String,
    val publisher: String,
    val description: String,
    val image: String,
)
