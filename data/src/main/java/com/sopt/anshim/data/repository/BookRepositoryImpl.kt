package com.sopt.anshim.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sopt.anshim.data.datasource.local.BookLocalDataSource
import com.sopt.anshim.data.datasource.remote.BookPagingSourceFactory
import com.sopt.anshim.data.mapper.toBook
import com.sopt.anshim.data.mapper.toBookEntity
import com.sopt.anshim.domain.model.Book
import com.sopt.anshim.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class BookRepositoryImpl @Inject constructor(
    private val bookLocalDataSource: BookLocalDataSource,
    private val pagingSourceFactory: BookPagingSourceFactory
): BookRepository {
    override suspend fun addBook(book: Book) {
        bookLocalDataSource.addBook(book.toBookEntity())
    }

    override fun getAllBooks(): Flow<List<Book>> {
        return bookLocalDataSource.getAllBooks().map { entities ->
            entities.map { it.toBook() }
        }
    }

    override suspend fun searchBooks(query: String): Flow<PagingData<Book>> {
        return Pager(
            config = PagingConfig(pageSize = 15, enablePlaceholders = false),
            pagingSourceFactory = { pagingSourceFactory.create(query) }
        ).flow
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