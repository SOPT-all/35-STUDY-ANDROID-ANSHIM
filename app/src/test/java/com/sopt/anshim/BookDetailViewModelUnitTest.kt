package com.sopt.anshim

import app.cash.turbine.test
import com.sopt.model.book.Book
import com.sopt.repository.BookRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.sopt.bookdetail.BookDetailViewModel
import org.sopt.bookdetail.Route

@RunWith(RobolectricTestRunner::class)
class BookDetailViewModelUnitTest {

    private lateinit var fakeBookRepository: BookRepository
    private lateinit var bookDetailViewModel: BookDetailViewModel
    private val bookId = 10L
    private val bookDetail: Book = Book(
        id = bookId,
        title = "title1",
        author = "author1",
        price = "price1",
        publisher = "publisher1",
        description = "description1",
        image = "image1"
    )

    @get:Rule
    val savedStateHandleRule = SavedStateHandleRule(Route.BookDetail(bookId = bookId))

    @Before
    fun setUp() {
        fakeBookRepository = mockk()
        coEvery { fakeBookRepository.getBook(bookId) } returns bookDetail

        bookDetailViewModel = BookDetailViewModel(fakeBookRepository,
            savedStateHandleRule.savedStateHandleMock)
    }

    @Test
    fun `book 변수 초기값이 모두 empty한지 테스트`() {
        assert(bookDetailViewModel.book.value == Book(
            0, "", "", "", "", "", "")
        )
    }

    @Test
    fun `book 변수가 Repository_getBook 값을 방출받았는지 테스트`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            bookDetailViewModel.book.collect {}
        }
        coVerify(exactly = 1, timeout = 5000) { fakeBookRepository.getBook(bookId) }

        bookDetailViewModel.book.test {
            val emittedValue = awaitItem()
            assert(emittedValue == bookDetail)
            cancelAndIgnoreRemainingEvents()
        }
    }
}