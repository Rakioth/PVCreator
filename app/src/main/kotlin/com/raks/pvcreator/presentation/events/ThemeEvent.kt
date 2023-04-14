package com.raks.pvcreator.presentation.events

sealed class ThemeEvent {
    data class ToggleDarkTheme(val theme: Boolean)        : ThemeEvent()
    data class UpdateLightToDarkRadius(val radius: Float) : ThemeEvent()
    data class UpdateDarkToLightRadius(val radius: Float) : ThemeEvent()
}
