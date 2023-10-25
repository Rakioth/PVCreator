package com.raks.pvcreator.presentation.events

sealed class ThemeEvent {
    object     ToggleThemeTransitionState                   : ThemeEvent()
    object     ToggleDarkTheme                              : ThemeEvent()
    data class UpdateLightToDarkRadius(val radius: Float)   : ThemeEvent()
    data class UpdateDarkToLightRadius(val radius: Float)   : ThemeEvent()
    data class StateReady(val isSystemInDarkTheme: Boolean) : ThemeEvent()
}