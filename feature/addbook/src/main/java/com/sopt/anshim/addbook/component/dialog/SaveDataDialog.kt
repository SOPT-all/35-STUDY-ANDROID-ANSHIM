package com.sopt.anshim.addbook.component.dialog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun SaveDataDialog(
    isVisible: Boolean = false,
    onConfirmClick: () -> Unit,
    onDenyClick: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(visible = isVisible) {
        CommonDialog(
            onConfirmClick = onConfirmClick,
            onDismissClick = onDenyClick,
            onDismissRequest = onDismissRequest,
            modifier = modifier
        ) {
            Text(
                text = "데이터를 임시저장하시겠습니까?"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GetSavedDataDialogPreview() {
    SaveDataDialog(
        isVisible = true,
        onConfirmClick = {},
        onDenyClick = {},
        onDismissRequest = {}
    )
}