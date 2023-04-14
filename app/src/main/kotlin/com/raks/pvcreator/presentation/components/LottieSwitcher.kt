package com.raks.pvcreator.presentation.components

import android.graphics.Bitmap
import android.view.View
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.core.graphics.applyCanvas
import androidx.core.graphics.createBitmap
import com.airbnb.lottie.compose.*
import com.raks.pvcreator.R
import com.raks.pvcreator.domain.model.ThemeIcon
import com.raks.pvcreator.presentation.events.ThemeEvent
import com.raks.pvcreator.presentation.viewmodels.ThemeViewModel
import com.raks.pvcreator.util.LocalTheme
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlin.math.roundToInt

@Composable
fun LottieSwitcher(
    viewModel:           ThemeViewModel,
    capturingViewBounds: Rect?,
    onPositioned:       (LayoutCoordinates) -> Unit,
    onScreenshot:       (Bitmap)            -> Unit,
    onSwitch:           ()                  -> Unit,
    darkTheme:           Boolean                    = LocalTheme.current,
) {
    val view      = LocalView.current
    val lastTheme = when (viewModel.state.lastTheme) {
        ThemeIcon.DEFAULT -> isSystemInDarkTheme()
        ThemeIcon.LIGHT   -> false
        ThemeIcon.DARK    -> true
    }
    val raw       = if (lastTheme) R.raw.pv_switch_dark else R.raw.pv_switch_light

    var isToggled   by remember { mutableStateOf(false) }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(raw))
    val progress    by animateLottieCompositionAsState(
        composition = composition,
        clipSpec    = LottieClipSpec.Frame(max = 50),
        speed       = if (isToggled) 1.0f else -1.0f,
    )

    LaunchedEffect(darkTheme) {
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
               .clickable(
                   onClickLabel = stringResource(R.string.label_switcher),
                   role         = Role.Switch,
                   onClick      = {
                       capturingViewBounds?.let {
                           onScreenshot(takeScreenShot(it, view))
                           if (!viewModel.state.themeConfig.isThemeActive) onSwitch()
                           viewModel.onEvent(ThemeEvent.ToggleDarkTheme(darkTheme))
                           isToggled = !isToggled
                       }
                   },
               ),
    )
}

private fun takeScreenShot(bounds: Rect, view: View): Bitmap =
    createBitmap(
        bounds.width.roundToInt(),
        bounds.height.roundToInt(),
        Bitmap.Config.ARGB_8888,
    ).applyCanvas { view.draw(this) }
