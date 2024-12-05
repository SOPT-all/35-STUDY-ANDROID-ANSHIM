package com.sopt.anshim.addbook.type

sealed class AddBookSideEffect{
    data object NavigateUp: AddBookSideEffect()
    data class ShowToast(val message: String): AddBookSideEffect()
}