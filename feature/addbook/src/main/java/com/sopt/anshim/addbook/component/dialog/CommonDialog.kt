package com.sopt.anshim.addbook.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
internal fun CommonDialog(
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(),
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Clear,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 10.dp)
                    .clickable { onDismissRequest() },
            )


            content()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = onDismissClick,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray
                    ),
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(text = "아니요")
                }

                Button(
                    onClick = onConfirmClick,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(text = "확인")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CommonDialogPreview() {
    CommonDialog(
        onConfirmClick = {},
        onDismissClick = {},
        onDismissRequest = {}
    ) {
        Text(text = "임시저장된 정보를 불러오겠습니까?")
    }
}