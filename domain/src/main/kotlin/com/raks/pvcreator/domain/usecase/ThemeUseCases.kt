package com.raks.pvcreator.domain.usecase

import com.raks.pvcreator.domain.usecase.theme.GetThemeConfig
import com.raks.pvcreator.domain.usecase.theme.SetTheme

data class ThemeUseCases(
    val getThemeConfig: GetThemeConfig,
    val setTheme:       SetTheme,
)
