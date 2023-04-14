package com.raks.pvcreator.util

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.raks.pvcreator.domain.model.ThemeIcon
import com.raks.pvcreator.presentation.states.ThemeState

@Composable
internal fun shouldUseDarkTheme(
    state: ThemeState,
): Boolean = when {
    state.isLoading -> isSystemInDarkTheme()
    else            -> when (state.themeConfig.themeIcon) {
        ThemeIcon.DEFAULT -> isSystemInDarkTheme()
        ThemeIcon.LIGHT   -> false
        ThemeIcon.DARK    -> true
    }
}
