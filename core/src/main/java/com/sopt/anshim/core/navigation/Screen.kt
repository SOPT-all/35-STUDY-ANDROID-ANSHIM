package com.sopt.anshim.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Home : Screen()

    @Serializable
    data object AddBook : Screen()

    @Serializable
    data class BookDetail(val bookId: Int) : Screen()

}