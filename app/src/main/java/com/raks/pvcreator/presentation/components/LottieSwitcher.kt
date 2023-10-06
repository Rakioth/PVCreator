package com.raks.pvcreator.presentation.components

import android.graphics.Bitmap
import android.view.View
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.graphics.applyCanvas
import com.airbnb.lottie.compose.*
import com.raks.pvcreator.R
import com.raks.pvcreator.presentation.events.ThemeEvent
import com.raks.pvcreator.ui.MainViewModel
import com.raks.pvcreator.util.LocalTheme
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlin.math.roundToInt

private var raw: Int = 0

@Composable
fun LottieSwitcher(
    viewModel:           MainViewModel,
    capturingViewBounds: Rect?,
    onPositioned:       (LayoutCoordinates) -> Unit,
    onScreenshot:       (Bitmap) -> Unit,
    darkTheme:           Boolean  = LocalTheme.current,
) {
    val view = LocalView.current

    if (raw == 0)
        raw = if (darkTheme) R.raw.pv_switch_dark else R.raw.pv_switch_light

    var isToggled   by remember { mutableStateOf(false) }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(raw))
    val progress    by animateLottieCompositionAsState(
        composition = composition,
        isPlaying   = true,
        clipSpec    = LottieClipSpec.Frame(max = 50),
        speed       = if (isToggled) 1.7f else -2.2f,
    )

    LaunchedEffect(viewModel.state?.startThemeTransition) {
        val tasks = arrayListOf<Deferred<Unit>>()

        tasks.add(async {
            animate(
                initialValue  = 0f,
                targetValue   = if (!darkTheme) 2500f else 0f,
                animationSpec = tween(1500),
                block         = { i, _ -> viewModel.onEvent(ThemeEvent.UpdateLightToDarkRadius(i)) })

        })

        tasks.add(async {
            animate(
                initialValue  = 2500f,
                targetValue   = if (darkTheme) 0f else 2500f,
                animationSpec = tween(1000),
                block         = { i, _ -> viewModel.onEvent(ThemeEvent.UpdateDarkToLightRadius(i)) })
        })

        tasks.awaitAll()
    }

    LottieAnimation(
        composition = composition,
        progress    = { progress },
        modifier    = Modifier
               .size(50.dp)
               .clip(CircleShape)
               .onGloballyPositioned { onPositioned(it) }
               .clickable {
                   capturingViewBounds?.let {
                       onScreenshot(takeScreenShot(it, view))
                       viewModel.onEvent(ThemeEvent.ToggleThemeTransitionState)
                       isToggled = !isToggled
                       viewModel.onEvent(ThemeEvent.ToggleDarkTheme)
                   }
               },
    )

}

private fun takeScreenShot(bounds: Rect, view: View) =
    Bitmap.createBitmap(
        bounds.width.roundToInt(),
        bounds.height.roundToInt(),
        Bitmap.Config.ARGB_8888,
    ).applyCanvas { view.draw(this) }