package com.sopt.anshim.main

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Home : Route

    @Serializable
    data object AddBook : Route
}
