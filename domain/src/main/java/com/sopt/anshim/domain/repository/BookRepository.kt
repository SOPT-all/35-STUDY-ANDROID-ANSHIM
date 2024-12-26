package com.sopt.anshim.domain.repository

import com.sopt.anshim.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun addBook(book: Book)
    fun getAllBooks(): Flow<List<Book>>
}