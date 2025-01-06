package com.sopt.anshim.data.datasource.remote

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookPagingSourceFactory @Inject constructor(
    private val remoteDataSource: BookRemoteDataSource
) {
    fun create(query: String): BookPagingSource {
        return BookPagingSource(remoteDataSource, query)
    }
}