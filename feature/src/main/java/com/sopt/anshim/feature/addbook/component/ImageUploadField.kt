package com.sopt.anshim.feature.addbook.component

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun ImageUploadField(
    imageUrl: MutableState<String>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    imageUrl.value = uri.toString()
                }
            }
        }
    )

    Button(
        modifier = modifier,
        onClick = { openImagePicker(imagePicker, context) },
    ) {
        Text("이미지 업로드")
    }
}

private fun openImagePicker(imagePicker: ActivityResultLauncher<Intent>, context: Context) {
    val intent = Intent(Intent.ACTION_PICK)
    intent.type = "image/*"
    imagePicker.launch(intent)
}