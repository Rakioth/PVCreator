package com.raks.pvcreator.domain.usecase.theme

import com.raks.pvcreator.domain.model.ThemeIcon
import com.raks.pvcreator.domain.repository.ThemeRepository

class ChangeTheme(
    private val repository: ThemeRepository
) {

    suspend operator fun invoke(themeIcon: ThemeIcon) =
        repository.changeTheme(themeIcon)

}