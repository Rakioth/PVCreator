package com.raks.pvcreator.domain.repository

import com.raks.pvcreator.domain.model.ThemeConfig
import com.raks.pvcreator.domain.model.ThemeIcon
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {

    val themeConfig: Flow<ThemeConfig>

    suspend fun setTheme(themeIcon: ThemeIcon)

}
