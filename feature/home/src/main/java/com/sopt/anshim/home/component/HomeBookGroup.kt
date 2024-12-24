package com.sopt.anshim.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.anshim.designsystem.component.image.UrlToBitmapImage
import com.sopt.model.book.Book

@Composable
internal fun HomeBookGroup(
    book: Book,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(3.5f),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        UrlToBitmapImage(
            imageUri = book.image,
            hint = "IMAGE",
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        ) {
            Text(
                text = book.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle.Default.copy(
                    fontSize = 20.sp
                )
            )
            Text(
                text = book.author,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle.Default.copy(
                    color = Color.Gray
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeBookGroupPreview() {
    HomeBookGroup(
        book = Book(
            title = "안심이 프로젝트",
            author = "정안심",
            image = "",
            price = "10,000",
            publisher = "",
            description = ""
        )
    )
}