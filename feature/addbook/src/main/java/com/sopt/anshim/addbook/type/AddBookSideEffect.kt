package com.sopt.anshim.addbook.type

/**
 * AddBookScreen의 사이드 이펙트
 *
 * [NavigateUp] 은 이전 화면으로 이동하는 효과
 * [ShowToast] 는 토스트 메시지를 보여주는 효과
 */
sealed class AddBookSideEffect{
    data object NavigateUp: AddBookSideEffect()
    data class ShowToast(val message: String): AddBookSideEffect()
}