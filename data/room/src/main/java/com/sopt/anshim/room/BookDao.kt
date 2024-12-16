package com.sopt.anshim.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(bookEntity: BookEntity)

    @Query("SELECT * FROM books")
    suspend fun getAllBooks(): List<BookEntity>

    @Delete
    suspend fun deleteBook(bookEntity: BookEntity)

}
