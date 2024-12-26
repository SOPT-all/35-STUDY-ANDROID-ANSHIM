package com.sopt.anshim.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sopt.anshim.addbook.AddBookScreen
import com.sopt.anshim.designsystem.theme.AnshimTheme
import com.sopt.anshim.home.HomeRoute
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.bookdetail.BookDetailScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnshimTheme {
                val navigator = rememberNavController()

                NavHost(
                    navController = navigator,
                    startDestination = Route.Home
                ) {
                    composable<Route.Home> {
                        HomeRoute(
                            navToAddBook = { navigator.navigate(Route.AddBook) },
                            navToDetail = { navigator.navigate(org.sopt.bookdetail.Route.BookDetail(it)) }
                        )
                    }

                    composable<Route.AddBook> {
                        AddBookScreen(
                            naviToHome = { navigator.navigateUp() }
                        )
                    }

                    composable<org.sopt.bookdetail.Route.BookDetail> {
                        BookDetailScreen(
                            modifier = Modifier.padding(24.dp)
                        )
                    }
                }
            }
        }
    }
}