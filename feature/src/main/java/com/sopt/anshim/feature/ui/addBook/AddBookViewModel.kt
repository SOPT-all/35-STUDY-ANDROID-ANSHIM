package com.sopt.anshim.feature.ui.addBook

import android.util.Log
import com.sopt.anshim.domain.model.Book
import com.sopt.anshim.feature.ui.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
//    val bookPreferences: BookPreferences,
) : BaseViewModel<AddBookContract.State, AddBookContract.Event, AddBookContract.Effect>(
    initialState = AddBookContract.State()
) {
    override fun reduceState(event: AddBookContract.Event) {
        when (event) {
            is AddBookContract.Event.SaveBook -> {
                Log.d("AddBookViewModel", "SaveBook event received")
                Log.d("AddBookViewModel", "event.book : ${event.book}")
                saveBook(event.book)
            }
            is AddBookContract.Event.SaveBookTemporarily -> {
                Log.d("AddBookViewModel", "SaveBookTemporarily event received")
                Log.d("AddBookViewModel", "event.book : ${event.book}")
                saveBookTemporarily(event.book)
            }
        }
    }

    private fun saveBook(book: Book) {
//        viewModelScope.launch {
//            bookPreferences.saveBook(book)
//        }
    }
    private fun saveBookTemporarily(book: Book) {
//        viewModelScope.launch {
//            bookPreferences.saveBookTemporarily(book)
//        }
    }
}