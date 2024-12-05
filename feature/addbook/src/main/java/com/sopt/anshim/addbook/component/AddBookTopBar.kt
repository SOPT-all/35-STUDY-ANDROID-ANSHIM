package com.sopt.anshim.addbook.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.anshim.addbook.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AddBookTopBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(R.string.add_book_top_bar_title))
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier
                    .size(32.dp)
                    .clickable { onBackClick() }
            )
        },
        windowInsets = WindowInsets(left = 10.dp, right = 10.dp),
        modifier = modifier.statusBarsPadding().shadow(elevation = 1.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewAddBookTopBar() {
    AddBookTopBar(
        onBackClick = {}
    )
}