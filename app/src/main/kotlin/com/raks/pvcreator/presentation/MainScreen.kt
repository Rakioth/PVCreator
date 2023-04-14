package com.raks.pvcreator.presentation

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import com.radusalagean.infobarcompose.InfoBarMessage
import com.raks.pvcreator.presentation.components.*
import com.raks.pvcreator.presentation.viewmodels.ThemeViewModel

@Composable
fun MainScreen(
    viewModel: ThemeViewModel
) {
    var capturingViewBounds by remember { mutableStateOf<Rect?>(null)              }
    var positionSwitcher    by remember { mutableStateOf<LayoutCoordinates?>(null) }
    var viewScreenshot      by remember { mutableStateOf<Bitmap?>(null)            }
    var message             by remember { mutableStateOf<InfoBarMessage?>(null)    }

    Scaffold(
        topBar = {
            TopBar(
                viewModel           = viewModel,
                capturingViewBounds = capturingViewBounds,
                onPositioned        = { positionSwitcher = it },
                onScreenshot        = { viewScreenshot   = it },
                onSwitch            = { message = InfoBarMessage(displayTimeSeconds = 3) },
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
                state    = viewModel.state,
                position = positionSwitcher,
                view     = viewScreenshot,
            )
            SweetToast(
                paddingValues = paddingValues,
                message       = message,
                onDismiss     = { message = null },
            )
        }
    }
}
