package com.raks.pvcreator.presentation.states

data class ThemeState(
    val isThemeActive:        Boolean = false,
    val isDarkTheme:          Boolean = false,
    val startThemeTransition: Boolean = false,
    val lightToDarkRadius:    Float   = 0f,
    val darkToLightRadius:    Float   = 0f,
)