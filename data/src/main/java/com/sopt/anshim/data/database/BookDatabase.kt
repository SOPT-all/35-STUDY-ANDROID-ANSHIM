package com.sopt.anshim.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sopt.anshim.data.database.dao.BookDao
import com.sopt.anshim.data.database.entity.BookEntity

@Database(
    entities = [BookEntity::class],
    version = 1
)
internal abstract class BookDatabase: RoomDatabase() {
    abstract fun bookDao(): BookDao
}