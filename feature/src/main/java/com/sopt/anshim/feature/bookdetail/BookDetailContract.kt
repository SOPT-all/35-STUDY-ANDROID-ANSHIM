package com.sopt.anshim.feature.bookdetail

import androidx.navigation.NavOptions
import com.sopt.anshim.core.navigation.Screen
import com.sopt.anshim.domain.model.Book
import com.sopt.anshim.feature.util.UiEffect
import com.sopt.anshim.feature.util.UiEvent
import com.sopt.anshim.feature.util.UiState

class BookDetailContract {
    data class State(
        val isLoading: Boolean = false,
        val book: Book = Book(
            title = "",
            author = "",
            imageUrl = "",
            price = 0,
            publisher = "",
            description = ""
        )
    ) : UiState

    sealed class Event : UiEvent {
        data object DeleteBook : Event()
        data object LoadBookDetail : Event()
    }

    sealed class Effect : UiEffect {
        data class ShowSnackBar(val message: String) : Effect()
        data class NavigateTo(
            val destination: Screen,
            val navOptions: NavOptions? = null
        ) : Effect()
    }
}