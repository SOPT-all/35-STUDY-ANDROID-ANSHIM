package com.sopt.anshim.data.datasource.local

import com.sopt.anshim.data.database.dao.BookDao
import com.sopt.anshim.data.database.entity.BookEntity
import javax.inject.Inject

internal class BookLocalDataSource @Inject constructor(
    private val bookDao: BookDao
) {
    suspend fun addBook(book: BookEntity) {
        bookDao.insertAreaCode(book)
    }
}