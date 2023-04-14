package com.raks.pvcreator.domain.usecase.theme

import com.raks.pvcreator.domain.model.ThemeConfig
import com.raks.pvcreator.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow

class GetThemeConfig(
    private val repository: ThemeRepository
) {

    operator fun invoke(): Flow<ThemeConfig> =
        repository.themeConfig

}
