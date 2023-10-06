package com.raks.pvcreator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.CompositionLocalProvider
import com.raks.pvcreator.presentation.MainScreen
import com.raks.pvcreator.presentation.events.ThemeEvent
import com.raks.pvcreator.ui.theme.PVCreatorTheme
import com.raks.pvcreator.util.LocalTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state               = viewModel.state
            val isSystemInDarkTheme = isSystemInDarkTheme()

//            viewModel.onEvent(ThemeEvent.StateReady(isSystemInDarkTheme))

            if (state != null)
                CompositionLocalProvider(LocalTheme provides state.isDarkTheme) {
                    PVCreatorTheme {
                        MainScreen(viewModel = viewModel)
                    }
                }
        }
    }

}