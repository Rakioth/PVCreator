package com.raks.pvcreator.domain.usecase

import com.raks.pvcreator.domain.usecase.theme.*

data class ThemeUseCases(
    val getThemeConfig: GetThemeConfig,
    val changeTheme:    ChangeTheme,
)