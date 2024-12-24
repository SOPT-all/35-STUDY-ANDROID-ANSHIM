package org.sopt.bookdetail

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data class BookDetail(val bookId: Long) : Route
}
