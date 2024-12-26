package com.sopt.anshim.feature.bookdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.sopt.anshim.feature.R
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BookDetailScreen(
    navigateToHome: () -> Unit,
    viewModel: BookDetailViewModel = hiltViewModel()
) {
    val effectFlow = viewModel.effect
    val bookDetailState by viewModel.uiState.collectAsStateWithLifecycle()
    viewModel.sendEvent(
        BookDetailContract.Event.LoadBookDetail
    )
    LaunchedEffect(Unit) {
        effectFlow.collectLatest { effect ->
            when (effect) {
                is BookDetailContract.Effect.NavigateTo ->
                    navigateToHome()

                is BookDetailContract.Effect.ShowSnackBar -> {
                    // 스낵바 보여주기
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val imagePainter = rememberAsyncImagePainter(
            model = bookDetailState.book.imageUrl
        )
        Image(
            painter = imagePainter,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(top = 16.dp)
                .border(
                    1.dp,
                    Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                ),
            contentScale = ContentScale.Fit
        )
        Text(
            text = bookDetailState.book.title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = stringResource(
                R.string.bookDetail_author,
                bookDetailState.book.author
            ),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = stringResource(
                R.string.bookDetail_publisher,
                bookDetailState.book.publisher ?: ""
            ),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Text(
            text = bookDetailState.book.description ?: "",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = stringResource(
                R.string.bookDetail_price,
                bookDetailState.book.price ?: 0
            ),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.primary
        )
        Button(
            onClick = {
                viewModel.sendEvent(
                    BookDetailContract.Event.DeleteBook
                )
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(R.string.bookDetail_delete))
        }
    }
}