package com.sopt.anshim.feature.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.anshim.core.navigation.Screen
import com.sopt.anshim.feature.home.HomeScreen
import com.sopt.anshim.feature.ui.addBook.AddBookScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home) {
        composable<Screen.Home> {
            HomeScreen(
                navigateToAddBook = {
                    navController.navigate(Screen.AddBook)
                }
            )
        }
        composable<Screen.AddBook> {
            AddBookScreen(
                navController
            )
        }
    }
}