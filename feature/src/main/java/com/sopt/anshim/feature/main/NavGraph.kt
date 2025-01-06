package com.sopt.anshim.feature.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.anshim.core.navigation.Screen
import com.sopt.anshim.feature.addbook.AddBookScreen
import com.sopt.anshim.feature.bookdetail.BookDetailScreen
import com.sopt.anshim.feature.booksearch.BookSearchScreen
import com.sopt.anshim.feature.home.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.BookSearch) {
        composable<Screen.Home> {
            HomeScreen(
                navigateToAddBook = {
                    navController.navigate(Screen.AddBook)
                }
            )
        }
        composable<Screen.AddBook> {
            AddBookScreen(
                navController = navController
            )
        }
        composable<Screen.BookDetail> {
            BookDetailScreen(
                navigateToHome = {
                    navController.navigate(Screen.Home)
                }
            )
        }
        composable<Screen.BookSearch> {
            BookSearchScreen()
        }
    }
}