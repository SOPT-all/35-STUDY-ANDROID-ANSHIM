package com.sopt.anshim.feature.booksearch

import androidx.paging.PagingData
import com.sopt.anshim.domain.model.Book
import com.sopt.anshim.feature.util.UiEffect
import com.sopt.anshim.feature.util.UiEvent
import com.sopt.anshim.feature.util.UiState
import kotlinx.coroutines.flow.Flow

class BookSearchContract {
    data class State(
        val isLoading: Boolean = false,
        val bookList: Flow<PagingData<Book>>? = null
    ) : UiState

    sealed class Event : UiEvent {
        data class SearchBook(val query: String) : Event()
    }

    sealed class Effect : UiEffect {
    }
}