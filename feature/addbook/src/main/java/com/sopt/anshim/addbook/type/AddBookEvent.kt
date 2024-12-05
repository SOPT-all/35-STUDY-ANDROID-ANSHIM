package com.sopt.anshim.addbook.type

sealed class AddBookEvent {
    data class TitleChanged(val newValue: String) : AddBookEvent()
    data class AuthorChanged(val newValue: String) : AddBookEvent()
    data class PriceChanged(val newValue: String) : AddBookEvent()
    data class PublisherChanged(val newValue: String) : AddBookEvent()
    data class DescriptionChanged(val newValue: String) : AddBookEvent()
}