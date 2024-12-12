package com.sopt.anshim.domain.repository

import com.sopt.anshim.domain.model.Book

interface BookRepository {
    suspend fun addBook(book: Book)
}