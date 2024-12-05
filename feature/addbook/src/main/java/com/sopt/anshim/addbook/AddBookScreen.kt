package com.sopt.anshim.addbook

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.sopt.anshim.addbook.component.AddBookImageField
import com.sopt.anshim.addbook.component.AddBookSaveButton
import com.sopt.anshim.addbook.component.AddBookTopBar
import com.sopt.anshim.addbook.component.TitledTextFieldGroup
import com.sopt.anshim.addbook.component.dialog.GetSavedDataDialog
import com.sopt.anshim.addbook.component.dialog.SaveDataDialog
import com.sopt.anshim.addbook.type.AddBookEvent
import com.sopt.anshim.addbook.type.AddBookSideEffect
import java.io.File

@Composable
fun AddBookScreen(
    modifier: Modifier = Modifier,
    naviToHome: () -> Unit = {},
    viewModel: AddHomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    val fileResultLauncher = uploadResultLauncher(
        onImageSelected = { dataUri ->
            val file = File(dataUri.toString())
            Log.d("ImageResult", "Brought ${file.absolutePath}")
            viewModel.onEvent(AddBookEvent.ImageChanged( newValue = dataUri))
        }
    )

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is AddBookSideEffect.NavigateUp -> {
                        naviToHome()
                    }

                    is AddBookSideEffect.ShowToast -> {
                        Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    BackHandler {
        viewModel.onEvent(AddBookEvent.BackButtonClicked)
    }

    AddBookScreen(
        imageUri = uiState.imageUri,
        title = uiState.title,
        author = uiState.author,
        price = uiState.price,
        publisher = uiState.publisher,
        description = uiState.description,
        onTitleChange = { newValue ->
            viewModel.onEvent(AddBookEvent.TitleChanged(newValue))
        },
        onAuthorChange = { newValue ->
            viewModel.onEvent(AddBookEvent.AuthorChanged(newValue))
        },
        onPriceChange = { newValue ->
            viewModel.onEvent(AddBookEvent.PriceChanged(newValue))
        },
        onPublisherChange = { newValue ->
            viewModel.onEvent(AddBookEvent.PublisherChanged(newValue))
        },
        onDescriptionChange = { newValue ->
            viewModel.onEvent(AddBookEvent.DescriptionChanged(newValue))
        },
        onBackClick = { viewModel.onEvent(AddBookEvent.BackButtonClicked) },
        onSaveButtonClick = { viewModel.onEvent(AddBookEvent.SaveButtonClicked) },
        onImageClick = {
            fileResultLauncher.launch("image/*")
        },
        modifier = modifier
    )

    GetSavedDataDialog(
        isVisible = uiState.getSavedDataDialogVisibility,
        onConfirmClick = { viewModel.onEvent(AddBookEvent.GetSavedDataDialogConfirmed) },
        onDismissRequest = { viewModel.onEvent(AddBookEvent.GetSavedDataDialogDismissed) }
    )

    SaveDataDialog(
        isVisible = uiState.saveDataDialogVisibility,
        onConfirmClick = { viewModel.onEvent(AddBookEvent.SaveDataDialogConfirmed) },
        onDismissRequest = { viewModel.onEvent(AddBookEvent.SaveDataDialogDismissed) }
    )
}

@Composable
private fun AddBookScreen(
    imageUri: Uri?,
    title: String,
    author: String,
    price: String,
    publisher: String,
    description: String,
    onTitleChange: (String) -> Unit,
    onAuthorChange: (String) -> Unit,
    onPriceChange: (String) -> Unit,
    onPublisherChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onImageClick: () -> Unit,
    onBackClick: () -> Unit,
    onSaveButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()


    Scaffold(
        topBar = {
            AddBookTopBar(
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            AddBookSaveButton(
                onClick = onSaveButtonClick
            )
        },
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
        ) {
            AddBookImageField(
                imageUri = imageUri,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f)
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    .clickable { onImageClick() },
            )

            TitledTextFieldGroup(
                title = stringResource(R.string.add_book_text_field_title),
                value = title,
                onValueChange = onTitleChange,
                isCompulsory = true,
                maxLines = 1,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
            )
            TitledTextFieldGroup(
                title = stringResource(R.string.add_book_text_field_author),
                value = author,
                onValueChange = onAuthorChange,
                isCompulsory = true,
                maxLines = 1,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
            )

            TitledTextFieldGroup(
                title = stringResource(R.string.add_book_text_field_price),
                value = price,
                onValueChange = onPriceChange,
                maxLines = 1,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
            )

            TitledTextFieldGroup(
                title = stringResource(R.string.add_book_text_field_publisher),
                value = publisher,
                onValueChange = onPublisherChange,
                maxLines = 1,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
            )

            TitledTextFieldGroup(
                title = stringResource(R.string.add_book_text_field_description),
                value = description,
                onValueChange = onDescriptionChange,
                maxLines = 4,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
            )
        }


    }
}

@Composable
private fun uploadResultLauncher(
    onImageSelected: (Uri) -> Unit,
) = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.GetContent()
) { uri: Uri? ->
    uri?.let(onImageSelected)
}

@Preview(showBackground = true)
@Composable
private fun PreviewAddBookScreen() {
    AddBookScreen(
        imageUri = null,
        title = "",
        author = "",
        price = "",
        publisher = "",
        description = "",
        onTitleChange = { },
        onAuthorChange = { },
        onPriceChange = { },
        onPublisherChange = { },
        onDescriptionChange = { },
        onImageClick = {},
        onBackClick = { },
        onSaveButtonClick = { }
    )
}
