package com.raks.pvcreator.presentation

import android.graphics.Bitmap
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import com.raks.pvcreator.presentation.components.*
import com.raks.pvcreator.ui.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    var capturingViewBounds by remember { mutableStateOf<Rect?>(null)              }
    var positionSwitcher    by remember { mutableStateOf<LayoutCoordinates?>(null) }
    var viewScreenshot      by remember { mutableStateOf<Bitmap?>(null)            }

    Scaffold(
        topBar = {
            TopBar(
                viewModel           = viewModel,
                capturingViewBounds = capturingViewBounds,
                onPositioned        = { positionSwitcher = it },
                onScreenshot        = { viewScreenshot   = it },
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned { capturingViewBounds = it.boundsInRoot() }
        ) {
            LottieBackground()
            PvLayout(paddingValues = paddingValues)
            AnimationSwitcher(
                state    = viewModel.state!!,
                position = positionSwitcher,
                view     = viewScreenshot,
            )
        }
    }
}