package com.sopt.anshim.addbook.state

import android.net.Uri

data class AddBookUiState(
    val imageUri: Uri? = null,
    val title: String = "",
    val author: String = "",
    val price: String = "",
    val publisher: String = "",
    val description: String = "",
    val getSavedDataDialogVisibility: Boolean = false,
    val saveDataDialogVisibility: Boolean = false,
)