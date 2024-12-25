package com.sopt.anshim.data.mapper

import com.sopt.anshim.data.database.entity.BookEntity
import com.sopt.anshim.domain.model.Book

internal fun BookEntity.toBook(): Book = Book(
    id = this.id,
    title = this.title,
    author = this.author,
    imageUrl = this.imageUrl,
    price = this.price,
    publisher = this.publisher,
    description = this.description
)