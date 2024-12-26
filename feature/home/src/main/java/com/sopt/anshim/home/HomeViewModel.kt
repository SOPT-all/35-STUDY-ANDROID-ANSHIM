package com.sopt.anshim.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sopt.anshim.home.contract.HomeUiEvent
import com.sopt.anshim.home.contract.HomeUiSideEffect
import com.sopt.anshim.home.contract.HomeUiState
import com.sopt.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<HomeUiSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    val books = bookRepository.searchBooks("코틀린")
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PagingData.empty()
        )

    fun handleEvent(event: HomeUiEvent) = viewModelScope.launch {
        when(event) {
            is HomeUiEvent.OnClickFAB -> {
                _sideEffect.emit(HomeUiSideEffect.NavigateToAddBook)
            }
            is HomeUiEvent.OnSelectBook -> {
                _sideEffect.emit(HomeUiSideEffect.NavigateToDetail(event.book.id))
            }
        }
    }
}