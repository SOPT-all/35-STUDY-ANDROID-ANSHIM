package com.sopt.anshim.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Book")
internal data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "book_title")
    val title: String,
    @ColumnInfo(name = "book_author")
    val author: String,
    @ColumnInfo(name = "book_image_url")
    val imageUrl: String? = null,
    @ColumnInfo(name = "book_price")
    val price: Int? = null,
    @ColumnInfo(name = "book_publisher")
    val publisher: String? = null,
    @ColumnInfo(name = "book_description")
    val description: String? = null
)