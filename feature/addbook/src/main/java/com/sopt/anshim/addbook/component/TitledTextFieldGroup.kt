package com.sopt.anshim.addbook.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.anshim.addbook.component.textfield.CommonTextField
import com.sopt.anshim.addbook.R

@Composable
internal fun TitledTextFieldGroup(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isCompulsory: Boolean = false,
    maxLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next
    ),
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title)
            if (isCompulsory) {
                Text(
                    text = stringResource(R.string.add_book_mark_compulsory),
                    color = Color.Red,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
        }
        CommonTextField(
            value = value,
            maxLines = maxLines,
            onValueChange = onValueChange,
            textStyle = TextStyle.Default.copy(lineHeight = 20.sp),
            keyboardOptions = keyboardOptions,
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MultiLineTextFieldGroupPreview() {
    TitledTextFieldGroup(
        title = "책 제목",
        value = "",
        isCompulsory = false,
        maxLines = 3,
        onValueChange = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun CompulsoryMultiLineTextFieldGroupPreview() {
    TitledTextFieldGroup(
        title = "책 제목",
        value = "",
        isCompulsory = true,
        maxLines = 1,
        onValueChange = {}
    )
}