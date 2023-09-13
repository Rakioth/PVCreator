package com.raks.pvcreator.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.raks.pvcreator.domain.repository.ThemeRepository
import com.raks.pvcreator.domain.usecase.ThemeUseCases
import com.raks.pvcreator.presentation.screen.pv.PvScreen
import com.raks.pvcreator.presentation.screen.splash.SplashScreen

@Composable
fun NavGraph(
    darkTheme: Boolean
) {
    val navController = rememberNavController()

    NavHost(
        navController    = navController,
        startDestination = Screen.PvScreen.route,
    ) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen()
        }
        composable(route = Screen.PvScreen.route) {
            PvScreen(darkTheme = darkTheme)
        }
    }
}