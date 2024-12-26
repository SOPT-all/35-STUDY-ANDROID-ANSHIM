package com.sopt.anshim.feature.home

import androidx.navigation.NavOptions
import com.sopt.anshim.core.navigation.Screen
import com.sopt.anshim.domain.model.Book
import com.sopt.anshim.feature.util.UiEffect
import com.sopt.anshim.feature.util.UiEvent
import com.sopt.anshim.feature.util.UiState

class HomeContract {
    data class State(
        val isLoading: Boolean = false,
        val bookList: List<Book>? = null
    ) : UiState

    sealed class Event : UiEvent {
        data class ClickBookDetail(val id: String) : Event()
    }

    sealed class Effect : UiEffect {
        data class ShowSnackBar(val message: String) : Effect()
        data class NavigateTo(
            val destination: Screen,
            val navOptions: NavOptions? = null
        ) : Effect()
    }
}