package com.sopt.anshim.feature.booksearch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.anshim.feature.home.component.BookItem

@Composable
fun BookSearchScreen(
    viewModel: BookSearchViewModel = hiltViewModel()
) {
    val bookDetailState by viewModel.uiState.collectAsStateWithLifecycle()
    val bookItems = bookDetailState.bookList?.collectAsLazyPagingItems()

    Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        var query by remember { mutableStateOf("") }

        TextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("검색어 입력") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { viewModel.sendEvent(BookSearchContract.Event.SearchBook(query)) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("검색")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            bookItems?.let { bookItems ->
                items(bookItems.itemCount) { index ->
                    bookItems[index]?.let {
                        BookItem(book = it)
                    }
                }
            }
        }
    }
}