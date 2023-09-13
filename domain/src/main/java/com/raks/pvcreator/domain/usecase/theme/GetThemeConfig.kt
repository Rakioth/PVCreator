package com.raks.pvcreator.domain.usecase.theme

import androidx.compose.runtime.State
import com.raks.pvcreator.domain.model.ThemeConfig
import com.raks.pvcreator.domain.repository.ThemeRepository

class GetThemeConfig(
    private val repository: ThemeRepository
) {

    operator fun invoke(): State<ThemeConfig> =
        repository.themeConfig

}