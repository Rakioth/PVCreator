package com.raks.pvcreator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import com.raks.pvcreator.presentation.navigation.NavGraph
import com.raks.pvcreator.presentation.screen.pv.PvViewModel
import com.raks.pvcreator.ui.theme.PVCreatorTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val pvViewModel by viewModels<PvViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("view 2 " + pvViewModel.hashCode())

        setContent {
//            val darkTheme = pvViewModel.themeState.value.isThemeDark
            val darkTheme = pvViewModel.getConfig

            PVCreatorTheme(darkTheme = darkTheme.value.isThemeDark) {
                NavGraph(darkTheme = darkTheme.value.isThemeDark)
            }
        }
    }

}