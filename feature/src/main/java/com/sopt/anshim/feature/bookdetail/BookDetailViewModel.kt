package com.sopt.anshim.feature.bookdetail

import androidx.lifecycle.viewModelScope
import com.sopt.anshim.core.navigation.Screen
import com.sopt.anshim.domain.model.Book
import com.sopt.anshim.domain.repository.BookRepository
import com.sopt.anshim.feature.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : BaseViewModel<BookDetailContract.State, BookDetailContract.Event, BookDetailContract.Effect>(
    initialState = BookDetailContract.State()
) {
    override fun reduceState(event: BookDetailContract.Event) {
        when (event) {
            is BookDetailContract.Event.DeleteBook -> {
                deleteBook(event.book)
            }
        }
    }

    private fun deleteBook(book: Book) = viewModelScope.launch {
        val result = runCatching {
            bookRepository.deleteBook(book)
        }

        result.onSuccess {
            postEffect(BookDetailContract.Effect.NavigateTo(Screen.Home))
        }.onFailure { exception ->
            postEffect(BookDetailContract.Effect.ShowSnackBar(exception.message ?: "Unknown error"))
        }
    }
}