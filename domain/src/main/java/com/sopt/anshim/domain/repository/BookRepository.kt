package com.sopt.anshim.domain.repository

import com.sopt.anshim.domain.model.Book

interface BookRepository {
    suspend fun saveBookTemporarily(book: Book)

    suspend fun getBookTemporarily(): Book
}