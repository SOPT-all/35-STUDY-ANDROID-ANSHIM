package com.sopt.anshim.designsystem.component.image

import android.graphics.ImageDecoder
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UrlToBitmapImage(
    imageUri: String,
    hint: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val bitmap = remember(imageUri) {
        //데이터베이스까지 수정돼야 할 것 같아서 일단 문자열 비교로 구현함
        if(imageUri.isNotBlank() && imageUri != "null"){
            val uri = Uri.parse(imageUri)
            ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(context.contentResolver, uri)
            )
        } else {
            null
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
                text = hint,
                color = Color.DarkGray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAddBookImageField() {
    UrlToBitmapImage(
        imageUri = "",
        hint = "image",
        modifier = Modifier
            .size(150.dp)
    )
}