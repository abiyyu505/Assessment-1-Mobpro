package com.abiyyu0003.asssessment1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abiyyu0003.asssessment1.ui.screen.MainScreen
import com.abiyyu0003.asssessment1.ui.screen.AboutScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(Screen.Main.route) {
            MainScreen(navController)
        }
        composable(Screen.About.route) {
            AboutScreen(navController)
        }
    }
}