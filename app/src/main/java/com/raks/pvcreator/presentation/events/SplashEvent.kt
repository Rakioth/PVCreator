package com.raks.pvcreator.presentation.events

sealed class SplashEvent {
    object ToggleThemeTransitionState                     : SplashEvent()
    object ToggleDarkTheme                                : SplashEvent()
    data class UpdateLightToDarkRadius(val radius: Float) : SplashEvent()
    data class UpdateDarkToLightRadius(val radius: Float) : SplashEvent()
}