package com.raks.pvcreator.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.raks.pvcreator.util.LocalTheme

private val DarkColorScheme  = darkColorScheme(
    inversePrimary     = InverseDark,
    primary            = LabelDark,
    primaryContainer   = BackgroundDark,
    secondary          = PickerSelectedTextDark,
    secondaryContainer = PickerSelectedBackgroundDark,
    tertiaryContainer  = PickerBackgroundDark,
    outline            = SeparatorDark,
    outlineVariant     = SystemBlue,
)

private val LightColorScheme = lightColorScheme(
    inversePrimary     = InverseLight,
    primary            = LabelLight,
    primaryContainer   = BackgroundLight,
    secondary          = PickerSelectedTextLight,
    secondaryContainer = PickerSelectedBackgroundLight,
    tertiaryContainer  = PickerBackgroundLight,
    outline            = SeparatorLight,
    outlineVariant     = SystemBlue,
)

@Composable
fun PVCreatorTheme(
    darkTheme: Boolean = LocalTheme.current,
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view        = LocalView.current

    if (!view.isInEditMode)
        SideEffect {
            val window = (view.context as Activity).window

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
                window.isNavigationBarContrastEnforced = false

            val windowsInsetsController = WindowCompat.getInsetsController(window, view)

            windowsInsetsController.isAppearanceLightStatusBars     = darkTheme
            windowsInsetsController.isAppearanceLightNavigationBars = darkTheme

            WindowCompat.setDecorFitsSystemWindows(window, false)
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography  = typography,
        content     = content,
    )
}
