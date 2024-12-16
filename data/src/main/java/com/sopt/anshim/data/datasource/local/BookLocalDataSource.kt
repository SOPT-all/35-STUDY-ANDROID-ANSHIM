package com.sopt.anshim.data.datasource.local

import com.sopt.anshim.data.model.BookData

interface BookLocalDataSource {
    suspend fun saveBookTemporarily(bookData: BookData)

    suspend fun getBookTemporarily(): BookData?
}
