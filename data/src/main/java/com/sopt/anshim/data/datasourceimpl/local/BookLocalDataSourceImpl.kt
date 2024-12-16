package com.sopt.anshim.data.datasourceimpl.local

import com.sopt.anshim.data.datasource.local.BookLocalDataSource
import com.sopt.anshim.data.datastore.BookDataStore
import com.sopt.anshim.data.model.BookData
import javax.inject.Inject

class BookLocalDataSourceImpl @Inject constructor(
    private val bookDataStore: BookDataStore,
) : BookLocalDataSource {
    override suspend fun saveBookTemporarily(bookData: BookData) =
        bookDataStore.saveBookTemporarily(bookData)

    override suspend fun getBookTemporarily(): BookData? =
        bookDataStore.getBookTemporarily()
}
