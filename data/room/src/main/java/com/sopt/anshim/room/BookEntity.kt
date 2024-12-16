package com.sopt.anshim.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "book_id") val id: Long = 0,
    @ColumnInfo(name = "image_uri") val imageUri: String,
    val title: String,
    val author: String,
    val price: String,
    val publisher: String,
    val description: String,
)
