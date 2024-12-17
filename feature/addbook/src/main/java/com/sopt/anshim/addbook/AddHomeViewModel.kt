package com.sopt.anshim.addbook

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.anshim.addbook.state.AddBookUiState
import com.sopt.anshim.addbook.type.AddBookEvent
import com.sopt.anshim.addbook.type.AddBookSideEffect
import com.sopt.model.book.Book
import com.sopt.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddHomeViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddBookUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<AddBookSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    fun onEvent(event: AddBookEvent) = viewModelScope.launch {
        when (event) {
            is AddBookEvent.ImageChanged -> updateImage(event.newValue)

            is AddBookEvent.TitleChanged -> updateTitle(event.newValue)

            is AddBookEvent.AuthorChanged -> updateAuthor(event.newValue)

            is AddBookEvent.PriceChanged -> updatePrice(event.newValue)

            is AddBookEvent.PublisherChanged -> updatePublisher(event.newValue)

            is AddBookEvent.DescriptionChanged -> updateDescription(event.newValue)

            is AddBookEvent.BackButtonClicked -> {
                if (isAllFieldBlank()) {
                    navigateUp()
                } else {
                    updateSaveDataDialogVisibility(true)
                }
            }

            is AddBookEvent.SavedDataExistenceChecked -> {
                if (isTemporarilySavedDataExists()) {
                    updateGetSavedDataDialogVisibility(true)
                }
            }

            is AddBookEvent.GetSavedDataDialogConfirmed -> {
                getTemporarilySavedData()
                updateGetSavedDataDialogVisibility(false)
            }

            is AddBookEvent.GetSavedDataDialogDismissed -> {
                deleteTemporarilySavedDate()
                updateGetSavedDataDialogVisibility(false)
            }

            is AddBookEvent.SaveDataDialogConfirmed -> {
                temporarilySaveData()
                updateSaveDataDialogVisibility(false)
                navigateUp()
            }

            is AddBookEvent.SaveDataDialogDenied -> {
                updateSaveDataDialogVisibility(false)
                navigateUp()
            }

            is AddBookEvent.SaveDataDialogDismissed -> updateSaveDataDialogVisibility(false)

            is AddBookEvent.SaveButtonClicked -> {
                saveData()
            }
        }
    }

    private fun updateImage(newValue: Uri) {
        _uiState.update { currentState ->
            currentState.copy(imageUri = newValue)
        }
    }

    private fun updateTitle(newValue: String) {
        if (newValue.length < MAX_TITLE_LENGTH) {
            _uiState.update { currentState ->
                currentState.copy(title = newValue)
            }
        }
    }

    private fun updateAuthor(newValue: String) {
        if (newValue.length < MAX_AUTHOR_LENGTH) {
            _uiState.update { currentState ->
                currentState.copy(author = newValue)
            }
        }
    }

    private fun updatePrice(newValue: String) {
        if (newValue.length < MAX_PRICE_LENGTH) {
            _uiState.update { currentState ->
                currentState.copy(price = newValue)
            }
        }
    }

    private fun updatePublisher(newValue: String) {
        if (newValue.length < MAX_PUBLISH_LENGTH) {
            _uiState.update { currentState ->
                currentState.copy(publisher = newValue)
            }
        }
    }

    private fun updateDescription(newValue: String) {
        if (newValue.length < MAX_DESCRIPTION_LENGTH) {
            _uiState.update { currentState ->
                currentState.copy(description = newValue)
            }
        }
    }

    private fun updateGetSavedDataDialogVisibility(newValue: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                getSavedDataDialogVisibility = newValue
            )
        }
    }

    private fun updateSaveDataDialogVisibility(newValue: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                saveDataDialogVisibility = newValue
            )
        }
    }

    private suspend fun isTemporarilySavedDataExists(): Boolean {
        val bookData = bookRepository.getBookTemporary()
        return bookData.title.isNotBlank()
    }

    private suspend fun getTemporarilySavedData() {
        with(bookRepository.getBookTemporary()) {
            _uiState.update { currentState ->
                currentState.copy(
                    imageUri = Uri.parse(image).takeIf { image.isNotBlank() },
                    title = title,
                    author = author,
                    price = price,
                    publisher = publisher,
                    description = description,

                    )
            }
        }
    }

    private suspend fun deleteTemporarilySavedDate() {
        bookRepository.saveBookTemporary(
            book = Book(
                image = "",
                title = "",
                author = "",
                price = "",
                description = "",
                publisher = ""
            )

        )
    }

    private suspend fun temporarilySaveData() {
        bookRepository.saveBookTemporary(
            book = with(_uiState.value) {
                Book(
                    image = imageUri?.path.orEmpty(),
                    title = title,
                    author = author,
                    price = price,
                    description = description,
                    publisher = publisher
                )
            }
        )
    }

    /**TODO: 사용자가 저장하기 버튼을 누르는 경우 */
    private suspend fun saveData() {
        with(_uiState.value) {
            if (title.isBlank() && author.isBlank()) {
                showToast(SAVE_DISMISS_MESSAGE)
            } else {
                showToast(SAVE_CONFIRM_MESSAGE)
                navigateUp()
            }
        }
    }

    private suspend fun showToast(message: String) {
        _sideEffect.emit(AddBookSideEffect.ShowToast(message))
    }

    private suspend fun navigateUp() {
        _sideEffect.emit(AddBookSideEffect.NavigateUp)
    }

    private fun isAllFieldBlank(): Boolean = with(_uiState.value) {
        title.isBlank() && author.isBlank() && price.isBlank() && publisher.isBlank() && description.isBlank()
    }

    companion object {
        private const val MAX_TITLE_LENGTH = 20
        private const val MAX_AUTHOR_LENGTH = 20
        private const val MAX_PRICE_LENGTH = 20
        private const val MAX_PUBLISH_LENGTH = 20
        private const val MAX_DESCRIPTION_LENGTH = 100

        private const val SAVE_DISMISS_MESSAGE = "* 표시된 항목은 모두 입력해 주세요."
        private const val SAVE_CONFIRM_MESSAGE = "저장되었습니다."
    }
}

