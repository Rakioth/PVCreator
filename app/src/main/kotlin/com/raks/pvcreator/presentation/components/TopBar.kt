package com.raks.pvcreator.presentation.components

import android.graphics.Bitmap
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import com.raks.pvcreator.presentation.viewmodels.ThemeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    viewModel:           ThemeViewModel,
    capturingViewBounds: Rect?,
    onPositioned:       (LayoutCoordinates) -> Unit,
    onScreenshot:       (Bitmap)            -> Unit,
    onSwitch:           ()                  -> Unit,
) {
    CenterAlignedTopAppBar(
        title  = {
            LottieSwitcher(
                viewModel           = viewModel,
                capturingViewBounds = capturingViewBounds,
                onPositioned        = onPositioned,
                onScreenshot        = onScreenshot,
                onSwitch            = onSwitch,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
    )
}
