package com.sopt.anshim.data.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sopt.anshim.data.mapper.toDomainModel
import com.sopt.anshim.domain.model.Book
import javax.inject.Inject

class BookPagingSource @Inject constructor(
    private val remoteDataSource: BookRemoteDataSource,
    private val query: String
) : PagingSource<Int, Book>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        return try {
            val start = params.key ?: 1
            val response = remoteDataSource.searchBooks(query, start).toDomainModel()

            LoadResult.Page(
                data = response,
                prevKey = if (start == 1) null else start - 15,
                nextKey = if (response.isEmpty()) null else start + 15
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val closestPage = state.closestPageToPosition(anchorPosition)
            closestPage?.prevKey?.plus(15) ?: closestPage?.nextKey?.minus(15)
        }
    }
}
