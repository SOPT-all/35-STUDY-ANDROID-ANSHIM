package org.sopt.bookdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.sopt.model.book.Book
import com.sopt.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    bookRepository: BookRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val arguments = savedStateHandle.toRoute<Route.BookDetail>()
    val bookId = arguments.bookId

    val book = flow {
        emit(bookRepository.getBook(bookId))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = Book(
            id = 0,
            title = "",
            author = "",
            price = "",
            publisher = "",
            description = "",
            image = ""
        )
    )
}
