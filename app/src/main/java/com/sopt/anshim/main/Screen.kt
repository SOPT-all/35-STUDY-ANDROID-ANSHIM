package com.sopt.anshim.main

import kotlinx.serialization.Serializable
@Serializable
sealed class Screen {
    @Serializable
    data object Home : Screen()

    @Serializable
    data object AddBook : Screen()

}