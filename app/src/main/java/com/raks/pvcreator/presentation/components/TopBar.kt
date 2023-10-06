package com.raks.pvcreator.presentation.components

import android.graphics.Bitmap
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import com.raks.pvcreator.ui.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    viewModel:           MainViewModel,
    capturingViewBounds: Rect?,
    onPositioned:       (LayoutCoordinates) -> Unit,
    onScreenshot:       (Bitmap) -> Unit,
) {
    CenterAlignedTopAppBar(
        title  = {
            LottieSwitcher(
                viewModel           = viewModel,
                capturingViewBounds = capturingViewBounds,
                onScreenshot        = { onScreenshot(it) },
                onPositioned        = { onPositioned(it) },
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
    )
}