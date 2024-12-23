package com.sopt.datasource.local

import com.sopt.anshim.room.BookDao
import com.sopt.datasource.mapper.toDomain
import com.sopt.datasource.mapper.toEntity
import com.sopt.datastore.BookDataStore
import com.sopt.model.book.Book
import javax.inject.Inject

class BookLocalDataSource @Inject constructor(
    private val bookDataStore: BookDataStore,
    private val bookDao: BookDao,
) {

    suspend fun saveBookTemporary(book: Book) {
        bookDataStore.saveBookTemporary(book)
    }

    suspend fun getBookTemporary(): Book? {
        return bookDataStore.getBookTemporary()
    }

    suspend fun saveBook(book: Book) {
        val bookEntity = book.toEntity()
        bookDao.insertBook(bookEntity)
    }

    suspend fun getAllBooks(): List<Book> =
        bookDao.getAllBooks().map { it.toDomain() }

    suspend fun deleteBook(book: Book) {
        val bookEntity = book.toEntity()
        bookDao.deleteBook(bookEntity)
    }
}

