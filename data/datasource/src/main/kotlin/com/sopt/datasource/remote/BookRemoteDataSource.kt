package com.sopt.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sopt.datasource.mapper.toDomain
import com.sopt.model.book.Book
import com.sopt.remote.BookApi
import javax.inject.Inject

class BookRemoteDataSource @Inject constructor(
    private val bookApi: BookApi
) {

    suspend fun fetchSearchedBooks(
        query: String,
        display: Int,
        start: Int,
    ): List<Book> {
        return bookApi.fetchSearchedBooks(
            "4Zxpt3MShHhqunUFXchK",
            "jqRQqkCN3i", query, display, start
        ).items.map { it.toDomain() }
    }
}

class BookPagingSource(
    private val bookRemoteDataSource: BookRemoteDataSource,
    private val query: String
) : PagingSource<Int, Book>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        val position = params.key ?: 1
        return try {
            val books = bookRemoteDataSource.fetchSearchedBooks(query, params.loadSize, position)
            LoadResult.Page(
                data = books,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (books.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}