package com.sopt.anshim.feature.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.anshim.domain.model.Book

private val dummyBookList = listOf(
    Book(
        title = "The Great Gatsby",
        author = "F. Scott Fitzgerald",
        imageUrl = "https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788936434595.jpg",
        price = 10000,
        publisher = "Scribner",
        description = "A novel about the decadence and excess of the Jazz Age. A novel about the decadence and excess of the Jazz Age"
    ),
    Book(
        title = "The Great Gatsby",
        author = "F. Scott Fitzgerald",
        imageUrl = "https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788936434595.jpg",
        price = 10000,
        publisher = "Scribner",
        description = "A novel about the decadence and excess of the Jazz Age. A novel about the decadence and excess of the Jazz Age"
    ),
    Book(
        title = "The Great Gatsby",
        author = "F. Scott Fitzgerald",
        imageUrl = "https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788936434595.jpg",
        price = 10000,
        publisher = "Scribner",
        description = "A novel about the decadence and excess of the Jazz Age. A novel about the decadence and excess of the Jazz Age"
    ),
    Book(
        title = "The Great Gatsby",
        author = "F. Scott Fitzgerald",
        imageUrl = "https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788936434595.jpg",
        price = 10000,
        publisher = "Scribner",
        description = "A novel about the decadence and excess of the Jazz Age. A novel about the decadence and excess of the Jazz Age"
    )
)

@Preview
@Composable
fun BookList(
    bookList: List<Book> = dummyBookList,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(bookList) { book ->
            BookItem(book = book)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}