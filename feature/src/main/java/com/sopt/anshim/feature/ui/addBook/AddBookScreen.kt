package com.sopt.anshim.feature.ui.addBook

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.sopt.anshim.domain.model.Book
import com.sopt.anshim.feature.R
import com.sopt.anshim.feature.ui.addBook.component.FilledTextField
import com.sopt.anshim.feature.ui.addBook.component.ImageUploadField
import com.sopt.anshim.feature.ui.addBook.component.NumericInputField
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddBookScreen(
    navController: NavController,
    viewModel: AddBookViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        effectFlow.collectLatest { effect ->
            when (effect) {
                is AddBookContract.Effect.NavigateTo -> {
                    navController.navigate(effect.destination, effect.navOptions)
                }

                is AddBookContract.Effect.ShowSnackBar -> {
                    // 스낵바 보여주기
                }
            }
        }
    }

    val title = remember { mutableStateOf("") }
    val author = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val imageUrl = remember { mutableStateOf("") }
    val price = remember { mutableIntStateOf(0) }
    val publisher = remember { mutableStateOf("") }


    Column {
        AddBookTopBar(
            stringResource(id = R.string.addBook_topBar)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            ImageUploadField(
                imageUrl = imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            )
            val imagePainter = rememberAsyncImagePainter(imageUrl.value)
            Image(
                painter = imagePainter,
                contentDescription = stringResource(id = R.string.addBook_image_upload_description),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(top = 16.dp)
                    .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(20.dp))
            FilledTextField(
                text = title.value,
                onValueChange = { title.value = it },
                label = stringResource(id = R.string.addBook_title)
            )
            Spacer(modifier = Modifier.height(20.dp))
            FilledTextField(
                text = author.value,
                onValueChange = { author.value = it },
                label = stringResource(id = R.string.addBook_author)
            )
            Spacer(modifier = Modifier.height(20.dp))
            FilledTextField(
                text = description.value,
                onValueChange = { description.value = it },
                label = stringResource(id = R.string.addBook_description)
            )
            Spacer(modifier = Modifier.height(20.dp))
            NumericInputField(
                value = price.intValue.toString(),
                onValueChange = { newValue ->
                    price.intValue = newValue.toIntOrNull() ?: 0
                },
                label = stringResource(id = R.string.addBook_price)
            )
            Spacer(modifier = Modifier.height(20.dp))
            FilledTextField(
                text = publisher.value,
                onValueChange = { publisher.value = it },
                label = stringResource(id = R.string.addBook_publisher)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        viewModel.sendEvent(
                            AddBookContract.Event.SaveBookTemporarily(
                                Book(
                                    title.value,
                                    author.value,
                                    imageUrl.value,
                                    price.intValue,
                                    publisher.value,
                                    description.value
                                )
                            )
                        )
                    },
                    enabled = title.value.isNotBlank() && author.value.isNotBlank(),
                    modifier = Modifier.padding(top = 8.dp)
                        .weight(1f)
                ) {
                    Text(stringResource(id = R.string.addBook_save))
                }

                Button(
                    onClick = {
                        if (title.value.isNotBlank() || author.value.isNotBlank()) {
                            viewModel.sendEvent(
                                AddBookContract.Event.SaveBookTemporarily(
                                    Book(
                                        title.value,
                                        author.value,
                                        imageUrl.value,
                                        price.intValue,
                                        publisher.value,
                                        description.value
                                    )
                                )
                            )
                        }
                    },
                    modifier = Modifier.padding(top = 8.dp)
                        .weight(1f)
                ) {
                    Text(stringResource(id = R.string.addBook_saveTemporarily))
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun AddBookTopBar(
    text: String
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Color.White,
        shadowElevation = 4.dp,
        shape = RectangleShape
    ) {
        Text(
            text,
            color = Color.Black,
            fontSize = 30.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AddBookScreenPreview() {
    val viewModel = AddBookViewModel()
    AddBookScreen(
        navController = NavController(LocalContext.current),
        viewModel = viewModel
    )
}