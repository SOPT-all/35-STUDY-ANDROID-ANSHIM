package com.sopt.anshim.data.service

import com.sopt.anshim.data.dto.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    @GET("book.json")
    suspend fun searchBooks(
        @Query("query") query: String,
        @Query("start") start: Int = 1,
        @Query("display") display: Int = 15
    ): BookResponse
}