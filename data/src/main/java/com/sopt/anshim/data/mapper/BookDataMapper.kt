package com.sopt.anshim.data.mapper

import com.sopt.anshim.data.model.BookData
import com.sopt.anshim.domain.model.Book

fun Book.toData(): BookData = BookData(
    title = this.title,
    author = this.author,
    imageUrl = this.imageUrl,
    price = this.price,
    publisher = this.publisher,
    description = this.description,
)

fun BookData.toDomain(): Book = Book(
    title = this.title ?: "",
    author = this.author ?: "",
    imageUrl = this.imageUrl,
    price = this.price,
    publisher = this.publisher,
    description = this.description,
)