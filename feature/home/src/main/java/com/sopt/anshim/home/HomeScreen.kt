package com.sopt.anshim.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.sopt.anshim.home.component.HomeBookGroup
import com.sopt.anshim.home.component.HomeFloatingButton
import com.sopt.anshim.home.component.HomeTopBar
import com.sopt.anshim.home.contract.HomeUiEvent
import com.sopt.anshim.home.contract.HomeUiSideEffect
import com.sopt.model.book.Book

@Composable
fun HomeRoute(
    navToAddBook: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navToDetail: (Book) -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect{ sideEffect ->
                when(sideEffect) {
                    HomeUiSideEffect.NavigateToAddBook -> {
                        navToAddBook()
                    }
                    is HomeUiSideEffect.NavigateToDetail -> {
                        navToDetail(sideEffect.book)
                    }
                }
            }
    }

    HomeScreen(
        bookList = uiState.books,
        onFabClick ={
            viewModel.handleEvent(HomeUiEvent.OnClickFAB)
        },
        onBookClick = { book ->
            viewModel.handleEvent(HomeUiEvent.OnSelectBook(book))
        },
        modifier = modifier
    )
}

@Composable
private fun HomeScreen(
    onBookClick: (Book) -> Unit,
    onFabClick: () -> Unit,
    bookList: List<Book>,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            HomeTopBar()
        },
        floatingActionButton = {
            HomeFloatingButton(onClick = onFabClick)
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp)
        ) {
            items(bookList) { book ->
                HomeBookGroup(
                    book = book,
                    modifier = Modifier.clickable {
                        onBookClick(book)
                    }
                )
            }
        }
    }
}


@Preview
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(
        onFabClick = {},
        onBookClick = {},
        bookList = listOf(
            Book(
                title = "안심이 프로젝트",
                author = "정안심",
                image = "",
                price = "10,000",
                publisher = "",
                description = ""
            ),
            Book(
                title = "안심이 프로젝트",
                author = "정안심",
                image = "",
                price = "10,000",
                publisher = "",
                description = ""
            ),
            Book(
                title = "안심이 프로젝트",
                author = "정안심",
                image = "",
                price = "10,000",
                publisher = "",
                description = ""
            ),
        )
    )
}