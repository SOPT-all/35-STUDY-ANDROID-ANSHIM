package com.sopt.anshim.data.model

import kotlinx.serialization.Serializable

@Serializable
data class BookData(
    val title: String? = null,
    val author: String? = null,
    val imageUrl: String? = null,
    val price: Int? = null,
    val publisher: String? = null,
    val description: String? = null
)
