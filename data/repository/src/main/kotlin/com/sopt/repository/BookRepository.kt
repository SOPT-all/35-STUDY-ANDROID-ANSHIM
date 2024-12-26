package com.sopt.repository

import androidx.paging.PagingData
import com.sopt.model.book.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun saveBookTemporary(book: Book)
    suspend fun getBookTemporary(): Book

    suspend fun saveBook(book: Book)
    suspend fun getAllBooks(): List<Book>
    suspend fun deleteBook(book: Book)

    suspend fun getBook(id: Long): Book

    fun searchBooks(query: String): Flow<PagingData<Book>>
}
