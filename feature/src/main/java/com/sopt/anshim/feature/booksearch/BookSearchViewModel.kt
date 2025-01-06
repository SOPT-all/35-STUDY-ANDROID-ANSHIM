package com.sopt.anshim.feature.booksearch

import androidx.lifecycle.viewModelScope
import com.sopt.anshim.domain.repository.BookRepository
import com.sopt.anshim.feature.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookSearchViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : BaseViewModel<BookSearchContract.State, BookSearchContract.Event, BookSearchContract.Effect>(
    initialState = BookSearchContract.State()
) {
    private fun searchBooks(query: String) {
        viewModelScope.launch {
            updateState(currentState.copy(isLoading = true))
            val result = runCatching {
                bookRepository.searchBooks(query)
            }
            result.onSuccess { pagingDataFlow ->
                updateState(
                    currentState.copy(
                        isLoading = false,
                        bookList = pagingDataFlow
                    )
                )
            }.onFailure {
                updateState(currentState.copy(isLoading = false))
                // TODO: 에러 상태 업데이트
            }
        }
    }

    override fun reduceState(event: BookSearchContract.Event) {
        when (event) {
            is BookSearchContract.Event.SearchBook ->
                searchBooks(event.query)
        }
    }
}