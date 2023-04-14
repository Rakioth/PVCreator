package com.raks.pvcreator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.raks.pvcreator.presentation.MainScreen
import com.raks.pvcreator.presentation.viewmodels.ThemeViewModel
import com.raks.pvcreator.ui.theme.PVCreatorTheme
import com.raks.pvcreator.util.LocalTheme
import com.raks.pvcreator.util.shouldUseDarkTheme
import com.raks.pvcreator.util.splashScreenExitAnimation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ThemeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition   { viewModel.state.isLoading     }
            setOnExitAnimationListener { splashScreenExitAnimation(it) }
        }

        setContent {
            val theme = shouldUseDarkTheme(viewModel.state)

            CompositionLocalProvider(LocalTheme provides theme) {
                PVCreatorTheme {
                    MainScreen(viewModel = viewModel)
                }
            }
        }
    }

}
