package com.sopt.anshim.addbook.component

import android.graphics.ImageDecoder
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.anshim.addbook.R

@Composable
internal fun AddBookImageField(
    imageUri: Uri?,
    modifier: Modifier
) {
    val context = LocalContext.current
    val bitmap = remember(imageUri) {
        imageUri?.let {
            ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(context.contentResolver, it)
            )
        }
    }

    if (bitmap != null) {
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = null,
            modifier = modifier
        )
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
        ) {
            Text(
                text = stringResource(R.string.add_book_image_hint),
                color = Color.DarkGray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAddBookImageField() {
    AddBookImageField(
        imageUri = null,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2f)
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
    )
}