package com.sopt.anshim.home.contract

import com.sopt.model.book.Book

sealed class HomeUiSideEffect {
    data object NavigateToAddBook: HomeUiSideEffect()
    data class NavigateToDetail(val book: Book): HomeUiSideEffect()
}