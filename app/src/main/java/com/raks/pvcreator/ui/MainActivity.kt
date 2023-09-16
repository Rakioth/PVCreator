package com.raks.pvcreator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import com.raks.pvcreator.domain.model.ThemeIcon
import com.raks.pvcreator.domain.model.ThemeConfig
import com.raks.pvcreator.presentation.navigation.NavGraph
import com.raks.pvcreator.presentation.screen.pv.PvViewModel
import com.raks.pvcreator.ui.theme.PVCreatorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<PvViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeConfig = viewModel.themeConfig.collectAsState(initial = ThemeConfig.default()).value
            val darkTheme   = themeConfig.themeIcon.let {
                when (it) {
                    ThemeIcon.DEFAULT -> isSystemInDarkTheme()
                    ThemeIcon.LIGHT   -> false
                    ThemeIcon.DARK    -> true
                }
            }

            PVCreatorTheme(darkTheme = darkTheme) {
                NavGraph(themeConfig = themeConfig)
            }
        }
    }

}