package com.sopt.anshim.data.mapper

import com.sopt.anshim.data.dto.BookResponse
import com.sopt.anshim.domain.model.Book

fun BookResponse.toDomainModel(): List<Book> {
    return this.items.map{
        Book(
            title = it.title,
            author = it.author,
            imageUrl = it.image,
            price = it.discount?.toInt(),
            publisher = it.publisher,
            description = it.description
        )
    }
}