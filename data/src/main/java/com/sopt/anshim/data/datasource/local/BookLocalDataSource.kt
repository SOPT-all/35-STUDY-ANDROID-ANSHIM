package com.sopt.anshim.data.datasource.local

import com.sopt.anshim.data.database.dao.BookDao
import com.sopt.anshim.data.database.entity.BookEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class BookLocalDataSource @Inject constructor(
    private val bookDao: BookDao
) {
    suspend fun addBook(book: BookEntity) {
        bookDao.insertBook(book)
    }

    fun getAllBooks(): Flow<List<BookEntity>> {
        return bookDao.getAllBooks()
    }
    suspend fun deleteBook(book: BookEntity) {
        bookDao.deleteBook(book)
    }

    suspend fun getBookById(id: Int): BookEntity? {
        return bookDao.getBookById(id)
    }
}