package com.sopt.anshim.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val author: String,
    val description: String,
    val discount: Int?,
    val image: String,
    val isbn: String,
    val link: Int,
    val pubdate: String,
    val publisher: String,
    val title: String
)