package com.sopt.repository

import com.sopt.model.book.Book

interface BookRepository {
    suspend fun saveBookTemporary(book: Book)
}