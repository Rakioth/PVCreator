package com.raks.pvcreator.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raks.pvcreator.domain.usecase.PvUseCases
import com.raks.pvcreator.domain.util.PvCreator
import com.raks.pvcreator.presentation.events.PvEvent
import com.raks.pvcreator.presentation.states.PvState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PvViewModel @Inject constructor(
    private val pvUseCases: PvUseCases
) : ViewModel() {

    var state by mutableStateOf(PvState())
        private set

    private val pvCreator = MutableStateFlow(PvCreator.default())

    init {
        viewModelScope.launch {
            state = state.copy(
                cards     = pvUseCases.getCards(),
                items     = pvUseCases.getItems(pvCreator.value.card),
                variants  = pvUseCases.getVariants(pvCreator.value.card),
                wildcards = pvUseCases.getWildcards(pvCreator.value.card),
                durations = pvUseCases.getDurations(),
            )

            pvCreator.collect {
                state = state.copy(
                    barcode = pvUseCases.getBarcode(it)
                )
            }
        }
    }

    fun onEvent(event: PvEvent) {
        when (event) {

            is PvEvent.InputCard     -> {
                state = when (event.pickerOption.id) {
                    PvCreator.TYPE_PINATA  -> state.copy(isPinataType = true,  isEggType = true,  isWeatherType = false)
                    PvCreator.TYPE_EGG     -> state.copy(isPinataType = false, isEggType = true,  isWeatherType = false)
                    PvCreator.TYPE_WEATHER -> state.copy(isPinataType = false, isEggType = false, isWeatherType = true)
                    else                   -> state.copy(isPinataType = false, isEggType = false, isWeatherType = false)
                }

                viewModelScope.launch {
                    state = state.copy(
                        items = pvUseCases.getItems(event.pickerOption.id)
                    )

                    pvCreator.update {
                        it.copy(
                            card = event.pickerOption.id
                        )
                    }
                }
            }

            is PvEvent.InputItem     -> {
                viewModelScope.launch {
                    if (pvCreator.value.card in setOf(PvCreator.TYPE_PINATA, PvCreator.TYPE_EGG))
                        state = state.copy(
                            variants  = pvUseCases.getVariants(event.pickerOption.id),
                            wildcards = pvUseCases.getWildcards(event.pickerOption.id),
                        )

                    pvCreator.update {
                        it.copy(
                            item = event.pickerOption.code!!
                        )
                    }
                }
            }

            is PvEvent.InputVariant  -> {
                pvCreator.update {
                    it.copy(
                        variant = event.pickerOption.code
                    )
                }
            }

            is PvEvent.InputWildcard -> {
                pvCreator.update {
                    it.copy(
                        wildcard = event.pickerOption.code
                    )
                }
            }

            is PvEvent.InputDuration -> {
                pvCreator.update {
                    it.copy(
                        duration = event.pickerOption.code!!
                    )
                }
            }

            is PvEvent.InputName     -> {
                pvCreator.update {
                    it.copy(
                        name = event.name
                    )
                }
            }

        }
    }

}
