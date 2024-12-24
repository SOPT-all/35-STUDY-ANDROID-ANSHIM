package org.sopt.bookdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.sopt.model.book.Book
import com.sopt.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    bookRepository: BookRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val arguments = savedStateHandle.toRoute<Route.BookDetail>()
    val bookId = arguments.bookId

    val book = flow {
        emit(bookRepository.getBookTemporary())
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = Book(
            title = "코틀린 인 액션",
            author = "드미트리 제메로프, 스베트라나 이사코바",
            price = "38000",
            publisher = "에이콘출판",
            description = "코틀린은 자바 가상 머신(JVM)에서 실행되는 언어로, 자바와 100% 호환되며 자바보다 간결하고 실용적인 기능을 제공합니다. 이 책은 코틀린의 기본 문법부터 함수형 프로그래밍, 리액티브 프로그래밍까지 다루며, 코틀린을 사용해 안드로이드 앱을 개발하는 방법을 알려줍니다.",
            image = "https://image.aladin.co.kr/product/21000/94/cover500/k492635485_1.jpg"
        )
    )
}
