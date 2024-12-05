package com.sopt.anshim.addbook

import androidx.lifecycle.ViewModel
import com.sopt.anshim.addbook.state.AddBookUiState
import com.sopt.anshim.addbook.type.AddBookEvent
import com.sopt.anshim.addbook.type.AddBookSideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddHomeViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState = MutableStateFlow(AddBookUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<AddBookSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    fun onEvent(event: AddBookEvent) {
        when (event) {
            is AddBookEvent.TitleChanged -> updateTitle(newValue = event.newValue)

            is AddBookEvent.AuthorChanged -> updateAuthor(newValue = event.newValue)

            is AddBookEvent.PriceChanged -> updatePrice(newValue = event.newValue)

            is AddBookEvent.PublisherChanged -> updatePublisher(newValue = event.newValue)

            is AddBookEvent.DescriptionChanged -> updateDescription(newValue = event.newValue)
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

    companion object {
        private const val MAX_TITLE_LENGTH = 20
        private const val MAX_AUTHOR_LENGTH = 20
        private const val MAX_PRICE_LENGTH = 20
        private const val MAX_PUBLISH_LENGTH = 20
        private const val MAX_DESCRIPTION_LENGTH = 100
    }
}

