package com.sopt.anshim.domain.repository

import androidx.paging.PagingData
import com.sopt.anshim.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun addBook(book: Book)
    fun getAllBooks(): Flow<List<Book>>
    suspend fun searchBooks(query: String): Flow<PagingData<Book>>
    suspend fun deleteBook(book: Book)
    suspend fun getBookById(id: Int): Book
}