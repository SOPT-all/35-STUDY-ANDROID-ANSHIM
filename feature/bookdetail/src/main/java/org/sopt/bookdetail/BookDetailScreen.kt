package org.sopt.bookdetail

import android.graphics.ImageDecoder
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.model.book.Book

@Composable
fun BookDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: BookDetailViewModel = hiltViewModel()
) {

    val book by viewModel.book.collectAsStateWithLifecycle()
    val context = LocalContext.current
//    val bitmap = remember {
//        ImageDecoder.decodeBitmap(
//            ImageDecoder.createSource(context.contentResolver, book.image.toUri())
//        )
//    }
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
//        Image(
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally)
//                .width(200.dp)
//                .aspectRatio(1f),
//            bitmap = bitmap.asImageBitmap(),
//            contentDescription = null
//        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(200.dp)
                .aspectRatio(1f)
                .background(Color.Blue)
        )

        Text(
            text = book.title,
            modifier = Modifier.padding(top = 16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )

        Text(
            text = book.author,
            modifier = Modifier.padding(top = 8.dp),
            fontSize = 16.sp
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text(
                text = book.publisher,
                modifier = Modifier,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = book.price + "원",
                modifier = Modifier,
                fontSize = 16.sp
            )
        }

        Text(
            text = book.description,
            modifier = Modifier.padding(top = 16.dp),
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BookDetailScreenPreview() {
    BookDetailScreen()
}