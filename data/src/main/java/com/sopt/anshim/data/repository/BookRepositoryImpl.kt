package com.sopt.anshim.data.repository

import com.sopt.anshim.data.datasource.local.BookLocalDataSource
import com.sopt.anshim.data.mapper.toBook
import com.sopt.anshim.data.mapper.toBookEntity
import com.sopt.anshim.domain.model.Book
import com.sopt.anshim.domain.repository.BookRepository
import javax.inject.Inject

internal class BookRepositoryImpl @Inject constructor(
    private val bookLocalDataSource: BookLocalDataSource
): BookRepository {
    override suspend fun addBook(book: Book) {
        bookLocalDataSource.addBook(book.toBookEntity())
    }

    override suspend fun deleteBook(book: Book) {
        bookLocalDataSource.deleteBook(book.toBookEntity())
    }

    override suspend fun getBookById(id: Int): Book {
        return bookLocalDataSource.getBookById(id)?.toBook() ?: Book(
            title = "",
            author = "",
            imageUrl = "",
            price = 0,
            publisher = "",
            description = ""
        )
    }
}