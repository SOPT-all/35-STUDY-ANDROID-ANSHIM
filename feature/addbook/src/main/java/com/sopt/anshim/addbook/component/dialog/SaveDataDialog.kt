package com.sopt.anshim.addbook.component.dialog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sopt.anshim.addbook.R

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

            Text(text = stringResource(R.string.add_book_dialog_save_data))
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