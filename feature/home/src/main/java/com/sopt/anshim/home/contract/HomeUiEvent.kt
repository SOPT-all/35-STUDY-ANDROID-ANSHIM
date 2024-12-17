package com.sopt.anshim.home.contract

import com.sopt.model.book.Book

sealed class HomeUiEvent {
    data object OnClickFAB: HomeUiEvent()
    data class OnSelectBook(val book: Book): HomeUiEvent()
}