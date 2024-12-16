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
class AddBookViewModel @Inject constructor(
    private val bookRepository: BookRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddBookUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<AddBookSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    fun onEvent(event: AddBookEvent) {
        when (event) {
            is AddBookEvent.ImageChanged -> updateImage(event.newValue)

            is AddBookEvent.TitleChanged -> updateTitle(event.newValue)

            is AddBookEvent.AuthorChanged -> updateAuthor(event.newValue)

            is AddBookEvent.PriceChanged -> updatePrice(event.newValue)

            is AddBookEvent.PublisherChanged -> updatePublisher(event.newValue)

            is AddBookEvent.DescriptionChanged -> updateDescription(event.newValue)

            is AddBookEvent.SavedDataExistenceChecked -> {
                if (checkTemporarilySavedDataExists()) {
                    updateGetSavedDataDialogVisibility(true)
                }
            }

            is AddBookEvent.GetSavedDataDialogConfirmed -> getTemporarilySavedData()

            is AddBookEvent.GetSavedDataDialogDismissed -> {
                deleteTemporarilySavedDate()
                updateGetSavedDataDialogVisibility(false)
            }

            is AddBookEvent.BackButtonClicked -> updateSaveDataDialogVisibility(true)

            is AddBookEvent.SaveDataDialogConfirmed -> {
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

    /**TODO: 로컬에 저장된 데이터가 있는지 확인 경우*/
    private fun checkTemporarilySavedDataExists(): Boolean {
        return false
    }

    /**TODO: 사용자가 임시저장 불러오기를 선택하는 경우 */
    private fun getTemporarilySavedData() {

    }

    /**TODO: 사용자가 임시저장 불러오기를 선택하지 않아 삭제되는 경우 */
    private fun deleteTemporarilySavedDate() {

    }

    /**TODO: 사용자가 저장하기 버튼을 누르는 경우 */
    private fun saveData() {
        viewModelScope.launch {
            with(_uiState.value) {
                if (title.isBlank() && author.isBlank()) {
                    showToast(SAVE_DISMISS_MESSAGE)
                } else {
                    showToast(SAVE_CONFIRM_MESSAGE)
                    bookRepository.saveBook(
                        book = Book(
                            title = _uiState.value.title,
                            author = _uiState.value.author,
                            price = _uiState.value.price,
                            publisher = _uiState.value.publisher,
                            description = _uiState.value.description,
                            image = _uiState.value.imageUri.toString()
                        )
                    )
                    navigateUp()
                }
            }
        }
    }

    private fun showToast(message: String) = viewModelScope.launch {
        _sideEffect.emit(AddBookSideEffect.ShowToast(message))
    }

    private fun navigateUp() = viewModelScope.launch {
        _sideEffect.emit(AddBookSideEffect.NavigateUp)
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
