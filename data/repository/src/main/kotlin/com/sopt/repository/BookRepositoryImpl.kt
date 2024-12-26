package com.sopt.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sopt.datasource.local.BookLocalDataSource
import com.sopt.datasource.remote.BookPagingSource
import com.sopt.datasource.remote.BookRemoteDataSource
import com.sopt.model.book.Book
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookLocalDataSource: BookLocalDataSource,
    private val bookRemoteDataSource: BookRemoteDataSource
) : BookRepository {

    override suspend fun saveBookTemporary(book: Book) {
        bookLocalDataSource.saveBookTemporary(book)
    }

    override suspend fun getBookTemporary(): Book {
        return bookLocalDataSource.getBookTemporary() ?: Book(
            id = 0L,
            title = "",
            author = "",
            price = "",
            publisher = "",
            description = "",
            image = ""
        )
    }

    override suspend fun saveBook(book: Book) =
        bookLocalDataSource.saveBook(book)

    override suspend fun getAllBooks(): List<Book> =
        bookLocalDataSource.getAllBooks()

    override suspend fun deleteBook(book: Book) =
        bookLocalDataSource.deleteBook(book)

    override suspend fun getBook(id: Long): Book {
        return bookLocalDataSource.getBook(id)
    }

    override fun searchBooks(query: String): Flow<PagingData<Book>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ), pagingSourceFactory = {
                BookPagingSource(
                    bookRemoteDataSource = bookRemoteDataSource,
                    query = query
                )
            }
        ).flow
    }
}
