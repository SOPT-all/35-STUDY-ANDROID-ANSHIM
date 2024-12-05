package com.sopt.datasource.local

import com.sopt.datastore.BookDataStore
import com.sopt.model.book.Book
import javax.inject.Inject

class BookLocalDataSource @Inject constructor(
    private val bookDataStore: BookDataStore
) {

    suspend fun saveBookTemporary(book: Book) {
        bookDataStore.saveBookTemporary(book)
    }
}