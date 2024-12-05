package com.sopt.anshim.addbook.state

data class AddBookUiState(
    val imageUri: String = "",
    val title: String = "",
    val author: String = "",
    val price: String = "",
    val publisher: String = "",
    val description: String = "",
    val getSavedDataDialogVisibility: Boolean = false,
    val saveDataDialogVisibility: Boolean = false,
)