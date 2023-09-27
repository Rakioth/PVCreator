package com.raks.pvcreator.presentation.screen.splash

import android.content.res.Configuration
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raks.pvcreator.domain.model.ThemeIcon
import com.raks.pvcreator.domain.usecase.ThemeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel : ViewModel() {

    private val _state: MutableState<SplashState?> = mutableStateOf(null)
    val state: State<SplashState?> = _state

    init {
        viewModelScope.launch {
                _state.value = SplashState(
                    isDarkTheme = true,
                    startThemeTransition = true,
                    lightToDarkRadius = 2500f,
                    darkToLightRadius = 2500f,
                )
        }
    }

    fun onEvent(event: SplashEvent) {
        when (event) {
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
//                    themeUseCases.changeTheme(if (state.value?.isDarkTheme!!) ThemeIcon.DARK else ThemeIcon.LIGHT)
                }
            }

            is SplashEvent.UpdateLightToDarkRadius    -> {
                _state.value = state.value?.copy(
                    darkToLightRadius = event.radius
                )
            }

            is SplashEvent.UpdateDarkToLightRadius    -> {
                _state.value = state.value?.copy(
                    lightToDarkRadius = event.radius
                )
            }
        }
    }


}