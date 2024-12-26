package com.sopt.anshim.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sopt.anshim.data.database.entity.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface BookDao {
    @Query("SELECT * FROM Book")
    fun getAllBooks(): Flow<List<BookEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: BookEntity)
    
    @Delete
    suspend fun deleteBook(book: BookEntity)

    @Query("SELECT * FROM Book WHERE id = :id")
    suspend fun getBookById(id: Int): BookEntity?
}