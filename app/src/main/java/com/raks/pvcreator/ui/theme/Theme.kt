package com.raks.pvcreator.ui.theme

import android.app.Activity
import android.content.res.Configuration
import android.os.Build
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    inversePrimary     = InverseDark,
    primary            = LabelDark,
    primaryContainer   = BackgroundDark,
    secondary          = PickerSelectedTextDark,
    secondaryContainer = PickerSelectedDark,
    tertiary           = PickerTextDark,
    tertiaryContainer  = PickerBackgroundDark,
    outline            = SeparatorDark,
    outlineVariant     = SystemBlue,
    surface            = PlasmaBlueDark,
    onSurface          = PlasmaGreenDark,
    surfaceVariant     = PlasmaYellowDark,
    onSurfaceVariant   = PlasmaRedDark,
    surfaceTint        = PlasmaPurpleDark,
)

private val LightColorScheme = lightColorScheme(
    inversePrimary     = InverseLight,
    primary            = LabelLight,
    primaryContainer   = BackgroundLight,
    secondary          = PickerSelectedTextLight,
    secondaryContainer = PickerSelectedLight,
    tertiary           = PickerTextLight,
    tertiaryContainer  = PickerBackgroundLight,
    outline            = SeparatorLight,
    outlineVariant     = SystemBlue,
    surface            = PlasmaBlueLight,
    onSurface          = PlasmaGreenLight,
    surfaceVariant     = PlasmaYellowLight,
    onSurfaceVariant   = PlasmaRedLight,
    surfaceTint        = PlasmaPurpleLight,
)

@Composable
fun PVCreatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme)
        DarkColorScheme
    else
        LightColorScheme

    if (darkTheme)
        LocalConfiguration.current.uiMode = Configuration.UI_MODE_NIGHT_YES
    else
        LocalConfiguration.current.uiMode = Configuration.UI_MODE_NIGHT_MASK

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Transparent.toArgb()
//            window.decorView.windowInsetsController?.
//            hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                window.isNavigationBarContrastEnforced = false
            }

            val windowsInsetsController = WindowCompat.getInsetsController(window, view)

            windowsInsetsController.isAppearanceLightStatusBars = darkTheme
            windowsInsetsController.isAppearanceLightNavigationBars = darkTheme


            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
    }

    MaterialTheme(
        colorScheme = animate(colorScheme),
        typography  = typography,
        content     = content,
    )
}

@Composable
private fun animate(colors: ColorScheme): ColorScheme {
    val animSpec = remember {
        spring<Color>(stiffness = 500f)
    }

    @Composable
    fun animateColor(color: Color): Color = animateColorAsState(targetValue = color, animationSpec = animSpec).value

    return ColorScheme(
        inversePrimary = animateColor(colors.inversePrimary),
        primary = animateColor(colors.primary),
        onPrimary = animateColor(colors.onPrimary),
        primaryContainer = animateColor(colors.primaryContainer),
        onPrimaryContainer = animateColor(colors.onPrimaryContainer),
        secondary = animateColor(colors.secondary),
        onSecondary = animateColor(colors.onSecondary),
        secondaryContainer = animateColor(colors.secondaryContainer),
        onSecondaryContainer = animateColor(colors.onSecondaryContainer),
        tertiary = animateColor(colors.tertiary),
        onTertiary = animateColor(colors.onTertiary),
        tertiaryContainer = animateColor(colors.tertiaryContainer),
        onTertiaryContainer = animateColor(colors.onTertiaryContainer),
        background = animateColor(colors.background),
        onBackground = animateColor(colors.onBackground),
        surface = animateColor(colors.surface),
        onSurface = animateColor(colors.onSurface),
        surfaceVariant = animateColor(colors.surfaceVariant),
        onSurfaceVariant = animateColor(colors.onSurfaceVariant),
        surfaceTint = animateColor(colors.surfaceTint),
        inverseSurface = animateColor(colors.inverseSurface),
        inverseOnSurface = animateColor(colors.inverseOnSurface),
        error = animateColor(colors.error),
        onError = animateColor(colors.onError),
        errorContainer = animateColor(colors.errorContainer),
        onErrorContainer = animateColor(colors.onErrorContainer),
        outline = animateColor(colors.outline),
        outlineVariant = animateColor(colors.outlineVariant),
        scrim = animateColor(colors.scrim)
    )
}