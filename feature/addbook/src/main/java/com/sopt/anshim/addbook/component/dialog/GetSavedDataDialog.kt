package com.sopt.anshim.addbook.component.dialog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.sopt.anshim.addbook.R

@Composable
internal fun GetSavedDataDialog(
    isVisible: Boolean = false,
    onConfirmClick: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(visible = isVisible) {
        CommonDialog(
            onConfirmClick = onConfirmClick,
            onDismissClick = onDismissRequest,
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ),
            modifier = modifier
        ) {
            Text(
                text = stringResource(R.string.add_book_dialog_get_data)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GetSavedDataDialogPreview() {
    GetSavedDataDialog(
        isVisible = true,
        onConfirmClick = {},
        onDismissRequest = {}
    )
}