package com.sopt.anshim.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sopt.anshim.data.database.entity.BookEntity

@Dao
internal interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAreaCode(book: BookEntity)
}