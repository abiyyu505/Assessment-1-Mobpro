package com.abiyyu0003.asssessment1.navigation

sealed class Screen(val route: String) {
    object Main: Screen("main")
    object About: Screen("about")
}