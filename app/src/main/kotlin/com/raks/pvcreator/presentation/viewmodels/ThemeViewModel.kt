package com.raks.pvcreator.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raks.pvcreator.domain.model.ThemeIcon
import com.raks.pvcreator.domain.usecase.ThemeUseCases
import com.raks.pvcreator.presentation.events.ThemeEvent
import com.raks.pvcreator.presentation.states.ThemeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val themeUseCases: ThemeUseCases
) : ViewModel() {

    var state by mutableStateOf(ThemeState())
        private set

    init {
        viewModelScope.launch {
            themeUseCases.getThemeConfig().take(1).collect {
                state = state.copy(
                    lastTheme = it.themeIcon,
                )
            }
            themeUseCases.getThemeConfig().collect {
                state = state.copy(
                    isLoading   = false,
                    themeConfig = it,
                )
            }
        }
    }

    fun onEvent(event: ThemeEvent) {
        when (event) {

            is ThemeEvent.ToggleDarkTheme         -> {
                viewModelScope.launch {
                    themeUseCases.setTheme(if (event.theme) ThemeIcon.LIGHT else ThemeIcon.DARK)
                }
            }

            is ThemeEvent.UpdateLightToDarkRadius -> {
                state = state.copy(
                    lightToDarkRadius = event.radius
                )
            }

            is ThemeEvent.UpdateDarkToLightRadius -> {
                state = state.copy(
                    darkToLightRadius = event.radius
                )
            }

        }
    }

}
