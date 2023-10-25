package com.raks.pvcreator.presentation.viewmodels

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raks.pvcreator.domain.model.ThemeIcon
import com.raks.pvcreator.domain.usecase.ThemeUseCases
import com.raks.pvcreator.presentation.events.ThemeEvent
import com.raks.pvcreator.presentation.states.ThemeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val themeUseCases: ThemeUseCases
) : ViewModel() {

    var state: ThemeState? by mutableStateOf(null)
        private set

    fun onEvent(event: ThemeEvent) {
        when (event) {

            is ThemeEvent.ToggleThemeTransitionState -> {
                state = state?.copy(
                    startThemeTransition = !state!!.startThemeTransition
                )
            }

            is ThemeEvent.ToggleDarkTheme            -> {
                viewModelScope.launch {
                    delay(20)
                    state = state?.copy(
                        isDarkTheme = !state!!.isDarkTheme
                    )
                    if (!state?.isThemeActive!!) delay(3000)
                    themeUseCases.setTheme(if (state!!.isDarkTheme) ThemeIcon.DARK else ThemeIcon.LIGHT)
                }
            }

            is ThemeEvent.UpdateLightToDarkRadius    -> {
                state = state?.copy(
                    lightToDarkRadius = event.radius
                )
            }

            is ThemeEvent.UpdateDarkToLightRadius    -> {
                state = state?.copy(
                    darkToLightRadius = event.radius
                )
            }

            is ThemeEvent.StateReady                 -> {
                viewModelScope.launch {
                    themeUseCases.getThemeConfig()
                        .take(1)
                        .collect {
                            val darkTheme = when (it.themeIcon) {
                                ThemeIcon.DEFAULT -> event.isSystemInDarkTheme
                                ThemeIcon.LIGHT   -> false
                                ThemeIcon.DARK    -> true
                            }

                            state = ThemeState(
                                isThemeActive        = it.isThemeActive,
                                isDarkTheme          = darkTheme,
                                startThemeTransition = darkTheme,
                                lightToDarkRadius    = 0f,
                                darkToLightRadius    = 0f,
                            )
                        }
                }
            }

        }
    }

}