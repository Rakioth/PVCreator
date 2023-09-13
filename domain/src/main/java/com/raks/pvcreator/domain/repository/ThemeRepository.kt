package com.raks.pvcreator.domain.repository

import androidx.compose.runtime.State
import com.raks.pvcreator.domain.model.ThemeConfig

interface ThemeRepository {

    var themeConfig: State<ThemeConfig>

    suspend fun changeTheme(value: Boolean)

}