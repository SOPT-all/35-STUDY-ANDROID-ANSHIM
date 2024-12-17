package com.sopt.anshim.addbook.type

import android.net.Uri

/**
 * AddBookScreen의 사용자 이벤트
 *
 * [TitleChanged] 는 도서 제목을 변경하는 이벤트
 * [AuthorChanged] 는 저자를 변경하는 이벤트
 * [PriceChanged] 는 가격을 변경하는 이벤트
 * [PublisherChanged] 는 출판사를 변경하는 이벤트
 * [DescriptionChanged] 는 소개를 변경하는 이벤트
 * [SavedDataExistenceChecked] 는 화면에 처음 진입했을 때 임시저장 정보가 있는지 확인하는 이벤트
 * [GetSavedDataDialogConfirmed] 는 임시저장 정보를 불러오는 이벤트
 * [GetSavedDataDialogDismissed] 는 임시저장 정보를 사용하지 않는 이벤트
 */

sealed class AddBookEvent {
    data class ImageChanged(val newValue: Uri): AddBookEvent()
    data class TitleChanged(val newValue: String) : AddBookEvent()
    data class AuthorChanged(val newValue: String) : AddBookEvent()
    data class PriceChanged(val newValue: String) : AddBookEvent()
    data class PublisherChanged(val newValue: String) : AddBookEvent()
    data class DescriptionChanged(val newValue: String) : AddBookEvent()
    data object SavedDataExistenceChecked: AddBookEvent()
    data object GetSavedDataDialogConfirmed: AddBookEvent()
    data object GetSavedDataDialogDismissed: AddBookEvent()
    data object BackButtonClicked : AddBookEvent()
    data object SaveDataDialogConfirmed: AddBookEvent()
    data object SaveDataDialogDenied: AddBookEvent()
    data object SaveDataDialogDismissed: AddBookEvent()
    data object SaveButtonClicked: AddBookEvent()
}