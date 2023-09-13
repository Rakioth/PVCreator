package com.raks.pvcreator.presentation.navigation

sealed class Screen(
    val route: String
) {
    object SplashScreen : Screen("splash_screen")
    object PvScreen     : Screen("pv_screen")
}