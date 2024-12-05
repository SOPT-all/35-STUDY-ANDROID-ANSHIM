package com.sopt.anshim.feature.ui.addBook

import androidx.navigation.NavOptions
import com.sopt.anshim.domain.model.Book
import com.sopt.anshim.feature.ui.util.UiEffect
import com.sopt.anshim.feature.ui.util.UiEvent
import com.sopt.anshim.feature.ui.util.UiState

class AddBookContract {
    data class State(
        val isLoading: Boolean = false,
        val book: Book? = null
    ) : UiState

    sealed class Event : UiEvent {
        data class SaveBook(val book: Book) : Event()
        data class SaveBookTemporarily(val book: Book) : Event()
    }

    sealed class Effect : UiEffect {
        data class ShowSnackBar(val message: String) : Effect()
        data class NavigateTo(
            val destination: String,
            val navOptions: NavOptions? = null
        ) : Effect()
    }
}