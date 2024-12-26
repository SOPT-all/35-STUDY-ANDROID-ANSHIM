package com.sopt.anshim.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.anshim.feature.R
import com.sopt.anshim.feature.home.component.BookList
import com.sopt.anshim.feature.home.component.HomeTopBar
import kotlinx.coroutines.flow.collectLatest

@Preview
@Composable
fun HomeScreen(
    navigateToAddBook: () -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect
    LaunchedEffect(Unit) {
        effectFlow.collectLatest { effect ->
            when (effect) {
                is HomeContract.Effect.NavigateTo -> {
                    navigateToAddBook()
                }

                is HomeContract.Effect.ShowSnackBar -> {
                    // ShowSnackBar
                }
            }
        }
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToAddBook() }
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = stringResource(R.string.home_fab_add_book)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeTopBar()
            uiState.bookList?.let { bookList ->
                BookList(
                    bookList = bookList
                )
            }
        }
    }
}