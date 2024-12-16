package com.sopt.anshim.data.repository

import com.sopt.anshim.data.datasource.local.BookLocalDataSource
import com.sopt.anshim.data.mapper.toData
import com.sopt.anshim.data.mapper.toDomain
import com.sopt.anshim.domain.model.Book
import com.sopt.anshim.domain.repository.BookRepository
import jakarta.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookLocalDataSource: BookLocalDataSource,
) : BookRepository {
    override suspend fun saveBookTemporarily(book: Book) =
        bookLocalDataSource.saveBookTemporarily(book.toData())

    override suspend fun getBookTemporarily(): Book =
        bookLocalDataSource.getBookTemporarily()?.toDomain() ?: Book(
            title = "",
            author = "",
            imageUrl = "",
            price = 0,
            publisher = "",
            description = "",
        )
}
