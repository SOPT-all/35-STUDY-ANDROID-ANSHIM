package com.sopt.repository

import com.sopt.datasource.local.BookLocalDataSource
import com.sopt.model.book.Book
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookLocalDataSource: BookLocalDataSource
) : BookRepository {

    override suspend fun saveBookTemporary(book: Book) {
        bookLocalDataSource.saveBookTemporary(book)
    }

    override suspend fun getBookTemporary(): Book {
        return bookLocalDataSource.getBookTemporary() ?: Book(
            title = "",
            author = "",
            price = "",
            publisher = "",
            description = "",
            image = ""
        )
    }
}