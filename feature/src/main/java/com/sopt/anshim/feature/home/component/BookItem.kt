package com.sopt.anshim.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sopt.anshim.core.util.price.toDecimalFormat
import com.sopt.anshim.domain.model.Book

@Composable
fun BookItem(
    book: Book,
    modifier: Modifier = Modifier,
    onBookDetailClick: () -> Unit = {}
) {
    val context = LocalContext.current
    Column (
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onBookDetailClick)
            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
            .padding(20.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = book.title,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF414141),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(53.dp))
            Text(
                text = book.author,
                fontWeight = FontWeight.Normal,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column (
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = book.description ?: "",
                    fontWeight = FontWeight.Normal,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = (book.price ?: 0).toDecimalFormat() + " 원",
                    fontWeight = FontWeight.Normal,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.width(32.dp))
            AsyncImage(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(10.dp)),
                model = ImageRequest.Builder(context)
                    .data(book.imageUrl)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = "",
            )

        }
    }
}