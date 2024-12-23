package com.sopt.datasource.mapper

import com.sopt.anshim.room.BookEntity
import com.sopt.model.book.Book

fun Book.toEntity(): BookEntity {
    return BookEntity(
        title = this.title,
        author = this.author,
        price = this.price,
        publisher = this.publisher,
        description = this.description,
        imageUri = this.image
    )
}

fun BookEntity.toDomain(): Book {
    return Book(
        title = this.title,
        author = this.author,
        price = this.price,
        publisher = this.publisher,
        description = this.description,
        image = this.imageUri
    )
}
