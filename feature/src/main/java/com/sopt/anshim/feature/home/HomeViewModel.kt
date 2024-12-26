package com.sopt.anshim.feature.home

import androidx.lifecycle.viewModelScope
import com.sopt.anshim.domain.repository.BookRepository
import com.sopt.anshim.feature.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : BaseViewModel<HomeContract.State, HomeContract.Event, HomeContract.Effect>(
    initialState = HomeContract.State()
) {
    override fun reduceState(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.ClickBookDetail -> {
                // 책 클릭 시 위치 이동
            }
        }
    }
    init {
        loadBookList()
    }
    private fun loadBookList() {
        viewModelScope.launch {
            bookRepository.getAllBooks()
                .collect { bookList ->
                    updateState(currentState.copy(bookList = bookList))
                }
        }
    }
}