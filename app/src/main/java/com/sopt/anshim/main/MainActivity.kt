package com.sopt.anshim.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sopt.anshim.addbook.AddBookScreen
import com.sopt.anshim.designsystem.theme.AnshimTheme
import com.sopt.anshim.home.HomeRoute
import dagger.hilt.android.AndroidEntryPoint

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
                            navToAddBook = { navigator.navigate(Route.AddBook) }
                        )
                    }

                    composable<Route.AddBook> {
                        AddBookScreen(
                            naviToHome = { navigator.navigate(Route.Home) }
                        )
                    }
                }
            }
        }
    }
}