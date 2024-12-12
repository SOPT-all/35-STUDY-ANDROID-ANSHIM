package com.sopt.anshim.feature.addbook

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.sopt.anshim.core.navigation.Screen
import com.sopt.anshim.domain.model.Book
import com.sopt.anshim.domain.repository.BookRepository
import com.sopt.anshim.feature.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : BaseViewModel<AddBookContract.State, AddBookContract.Event, AddBookContract.Effect>(
    initialState = AddBookContract.State()
) {
    override fun reduceState(event: AddBookContract.Event) {
        when (event) {
            is AddBookContract.Event.SaveBook -> {
                saveBook(event.book)
            }

            is AddBookContract.Event.SaveBookTemporarily -> {
                Log.d("AddBookViewModel", "SaveBookTemporarily event received")
                Log.d("AddBookViewModel", "event.book : ${event.book}")
                saveBookTemporarily(event.book)
            }
        }
    }

    private fun saveBook(book: Book) = viewModelScope.launch {
        val result = runCatching {
            bookRepository.addBook(book)
        }

        result.onSuccess {
            postEffect(AddBookContract.Effect.NavigateTo(Screen.Home))
        }.onFailure { exception ->
            postEffect(AddBookContract.Effect.ShowSnackBar(exception.message ?: "Unknown error"))
        }
    }

    private fun saveBookTemporarily(book: Book) {
//        viewModelScope.launch {
//            bookPreferences.saveBookTemporarily(book)
//        }
    }
}