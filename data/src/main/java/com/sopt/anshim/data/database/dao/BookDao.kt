package com.sopt.anshim.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.sopt.anshim.data.database.entity.BookEntity

@Dao
internal interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAreaCode(book: BookEntity)

    @Delete
    suspend fun deleteBook(book: BookEntity)
}