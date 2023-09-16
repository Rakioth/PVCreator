package com.raks.pvcreator.domain.model

data class ThemeConfig(
    val isThemeActive: Boolean,
    val themeIcon:     ThemeIcon,
) {

    companion object {
        fun default() = ThemeConfig(false, ThemeIcon.DEFAULT)
    }

}