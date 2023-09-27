package com.raks.pvcreator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.raks.pvcreator.presentation.navigation.NavGraph
import com.raks.pvcreator.presentation.screen.splash.SplashViewModel
import com.raks.pvcreator.ui.theme.PVCreatorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state = viewModel.state.value

            if (state != null)
                PVCreatorTheme(darkTheme = state.isDarkTheme) {
                    NavGraph()
                }
        }
    }

}