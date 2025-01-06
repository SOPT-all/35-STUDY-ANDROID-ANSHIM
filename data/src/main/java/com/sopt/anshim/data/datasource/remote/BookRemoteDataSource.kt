package com.sopt.anshim.data.datasource.remote

import com.sopt.anshim.data.dto.BookResponse
import com.sopt.anshim.data.service.BookService
import javax.inject.Inject

class BookRemoteDataSource @Inject constructor(
    private val bookService: BookService
) {
    suspend fun searchBooks(query: String, start: Int): BookResponse {
        return bookService.searchBooks(query, start)
    }
}