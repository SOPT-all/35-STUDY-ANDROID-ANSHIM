package com.sopt.anshim.domain.model

data class Book(
    val title: String,
    val author: String,
    val imageUrl: String? = null,
    val price: Int? = null,
    val publisher: String? = null,
    val description: String? = null
)