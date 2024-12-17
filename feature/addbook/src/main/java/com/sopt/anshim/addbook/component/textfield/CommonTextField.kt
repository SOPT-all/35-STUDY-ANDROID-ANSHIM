package com.sopt.anshim.addbook.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun CommonTextField(
    value: String,
    modifier: Modifier = Modifier,
    cursorBrush: Brush = SolidColor(Color.Blue),
    maxLines: Int = 1,
    contentColor: Color = Color.White,
    valueColor: Color = Color.Black,
    onValueChange: (String) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next
    ),
    textStyle: TextStyle = TextStyle.Default.copy(color = valueColor),
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {

    val heightInDp = (textStyle.lineHeight.value * maxLines).dp
    BasicTextField(
        maxLines = maxLines,
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        cursorBrush = cursorBrush,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        modifier = modifier
            .background(
                color = contentColor,
                shape = RoundedCornerShape(5.dp)
            )
            .border(width = Dp.Hairline, brush = cursorBrush, shape = RoundedCornerShape(5.dp))
            .padding(10.dp)
            .height(heightInDp)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewCommonTextField() {
    CommonTextField(
        value = "",
        textStyle = TextStyle.Default.copy(lineHeight = 20.sp),
        maxLines = 3,
        modifier = Modifier.fillMaxWidth()
    )
}