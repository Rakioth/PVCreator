package com.raks.pvcreator.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.raks.pvcreator.presentation.screen.pv.PvScreen
import com.raks.pvcreator.presentation.screen.splash.SplashScreen
import com.raks.pvcreator.presentation.screen.splash.SplashViewModel
import com.raks.pvcreator.presentation.screen.splash.components.SplashTopBar
import com.raks.pvcreator.ui.MainViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController    = navController,
        startDestination = Screen.PvScreen.route,
    ) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.PvScreen.route) {
            SplashTopBar()
        }
    }
}