package com.sopt.anshim.feature.bookdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.sopt.anshim.core.navigation.Screen
import com.sopt.anshim.domain.repository.BookRepository
import com.sopt.anshim.feature.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bookRepository: BookRepository
) : BaseViewModel<BookDetailContract.State, BookDetailContract.Event, BookDetailContract.Effect>(
    initialState = BookDetailContract.State()
) {
    private val arguments = savedStateHandle.toRoute<Screen.BookDetail>()
    private val bookId = arguments.bookId
    override fun reduceState(event: BookDetailContract.Event) {
        when (event) {
            is BookDetailContract.Event.DeleteBook -> {
                deleteBook()
            }

            BookDetailContract.Event.LoadBookDetail -> {
                loadBookDetail()
            }
        }
    }

    private fun loadBookDetail() = viewModelScope.launch {
        val result = runCatching {
            val bookDetail = bookRepository.getBookById(bookId)
            updateState(currentState.copy(book = bookDetail))
        }
        result.onFailure { exception ->
            postEffect(BookDetailContract.Effect.ShowSnackBar(exception.message ?: "Unknown error"))
        }
    }

    private fun deleteBook() = viewModelScope.launch {
        val result = runCatching {
            bookRepository.deleteBook(currentState.book)
        }

        result.onSuccess {
            postEffect(BookDetailContract.Effect.NavigateTo(Screen.Home))
        }.onFailure { exception ->
            postEffect(BookDetailContract.Effect.ShowSnackBar(exception.message ?: "Unknown error"))
        }
    }
}