package com.sopt.remote

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BookApi {

    @GET("v1/search/book.json")
    suspend fun fetchSearchedBooks(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("query") query: String,
        @Query("display") display: Int,
        @Query("start") start: Int,
    ): BookPaginationDto
}