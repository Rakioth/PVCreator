package com.raks.pvcreator.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raks.pvcreator.domain.model.ThemeIcon
import com.raks.pvcreator.domain.usecase.ThemeUseCases
import com.raks.pvcreator.presentation.screen.splash.SplashEvent
import com.raks.pvcreator.presentation.screen.splash.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val themeUseCases: ThemeUseCases,
) : ViewModel() {
    private val _state: MutableState<SplashState?> = mutableStateOf(null)
    val state: State<SplashState?> = _state

    init {
        viewModelScope.launch {
            themeUseCases.getThemeConfig().take(1).collect {

                val darktheme = when(it.themeIcon) {
                    ThemeIcon.DEFAULT -> true
                    ThemeIcon.LIGHT -> false
                    ThemeIcon.DARK -> true
                }


                _state.value = SplashState(
                    isThemeActive = it.isThemeActive,
                    isDarkTheme = darktheme,
                    startThemeTransition = darktheme,
                    lightToDarkRadius = 2500f,
                    darkToLightRadius = 2500f,
                )
            }

        }
    }

    fun onEvent(events: SplashEvent) {
        when (events) {

            is SplashEvent.ToggleThemeTransitionState -> {
                _state.value = state.value?.copy(
                    startThemeTransition = !state.value!!.startThemeTransition
                )
            }

            is SplashEvent.ToggleDarkTheme            -> {
                viewModelScope.launch {
                    delay(20)
                    _state.value = state.value?.copy(
                        isDarkTheme = !state.value!!.isDarkTheme
                    )
                    themeUseCases.setTheme(if (state.value!!.isDarkTheme) ThemeIcon.DARK else ThemeIcon.LIGHT)
                }
            }

            is SplashEvent.UpdateDarkToLightRadius    -> {
                _state.value = state.value?.copy(
                    darkToLightRadius = events.radius
                )
            }

            is SplashEvent.UpdateLightToDarkRadius    -> {
                _state.value = state.value?.copy(
                    lightToDarkRadius = events.radius
                )
            }

        }
    }
}