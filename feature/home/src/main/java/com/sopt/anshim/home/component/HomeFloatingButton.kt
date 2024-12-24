package com.sopt.anshim.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun HomeFloatingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 3.dp
        ),
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add Book"
        )
    }
}

@Preview
@Composable
private fun PreviewHomeFloatingButton() {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(color = Color.White)
            .padding(20.dp)
    ) {
        HomeFloatingButton(
            onClick = {}
        )
    }
}