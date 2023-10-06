package com.raks.pvcreator.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raks.pvcreator.presentation.events.ThemeEvent
import com.raks.pvcreator.presentation.states.ThemeState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ThemeViewModel : ViewModel() {

    private val _state: MutableState<ThemeState?> = mutableStateOf(null)
    val state: State<ThemeState?> = _state

    init {
        viewModelScope.launch {
                _state.value = ThemeState(
                    isDarkTheme = true,
                    startThemeTransition = true,
                    lightToDarkRadius = 2500f,
                    darkToLightRadius = 2500f,
                )
        }
    }

    fun onEvent(event: ThemeEvent) {
        when (event) {
            is ThemeEvent.ToggleThemeTransitionState -> {
                _state.value = state.value?.copy(
                    startThemeTransition = !state.value!!.startThemeTransition
                )
            }

            is ThemeEvent.ToggleDarkTheme            -> {
                viewModelScope.launch {
                    delay(20)
                    _state.value = state.value?.copy(
                        isDarkTheme = !state.value!!.isDarkTheme
                    )
//                    themeUseCases.changeTheme(if (state.value?.isDarkTheme!!) ThemeIcon.DARK else ThemeIcon.LIGHT)
                }
            }

            is ThemeEvent.UpdateLightToDarkRadius    -> {
                _state.value = state.value?.copy(
                    darkToLightRadius = event.radius
                )
            }

            is ThemeEvent.UpdateDarkToLightRadius    -> {
                _state.value = state.value?.copy(
                    lightToDarkRadius = event.radius
                )
            }

            is ThemeEvent.StateReady                 -> TODO()
        }
    }


}