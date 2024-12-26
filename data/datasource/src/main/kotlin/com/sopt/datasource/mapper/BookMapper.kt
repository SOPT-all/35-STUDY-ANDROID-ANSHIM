package com.sopt.datasource.mapper

import com.sopt.anshim.room.BookEntity
import com.sopt.model.book.Book
import com.sopt.remote.BookDto

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
        id = this.id,
        title = this.title,
        author = this.author,
        price = this.price,
        publisher = this.publisher,
        description = this.description,
        image = this.imageUri
    )
}

fun BookDto.toDomain(): Book {
    return Book(
        id = isbn.toLong(),
        title = this.title,
        author = this.author,
        price = this.discount,
        publisher = this.publisher,
        description = this.description,
        image = this.image
    )
}
