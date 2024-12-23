package com.sopt.repository

import com.sopt.model.book.Book

interface BookRepository {
    suspend fun saveBookTemporary(book: Book)
    suspend fun getBookTemporary(): Book

    suspend fun saveBook(book: Book)
    suspend fun getAllBooks(): List<Book>
    suspend fun deleteBook(book: Book)
}
