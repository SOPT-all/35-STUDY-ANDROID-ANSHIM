package com.sopt.anshim.domain.repository

import com.sopt.anshim.domain.model.Book

interface BookRepository {
    suspend fun addBook(book: Book)
    suspend fun searchBooks(query: String): Result<List<Book>>
    suspend fun deleteBook(book: Book)
    suspend fun getBookById(id: Int): Book
}