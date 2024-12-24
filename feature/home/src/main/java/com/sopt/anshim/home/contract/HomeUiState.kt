package com.sopt.anshim.home.contract

import com.sopt.model.book.Book

data class HomeUiState(
    val books: List<Book> = emptyList()
)