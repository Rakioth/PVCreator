package com.raks.pvcreator.presentation.states

import com.raks.pvcreator.domain.model.ThemeConfig
import com.raks.pvcreator.domain.model.ThemeIcon

data class ThemeState(
    val isLoading:         Boolean     = true,
    val lastTheme:         ThemeIcon   = ThemeIcon.DEFAULT,
    val themeConfig:       ThemeConfig = ThemeConfig(
        isThemeActive = false,
        themeIcon     = ThemeIcon.DEFAULT,
    ),
    val lightToDarkRadius: Float       = 0f,
    val darkToLightRadius: Float       = 0f,
)
