package com.raks.pvcreator.domain.usecase.theme

import com.raks.pvcreator.domain.repository.ThemeRepository

class ChangeTheme(
    private val repository: ThemeRepository
) {

    suspend operator fun invoke(value: Boolean) =
        repository.changeTheme(value)

}