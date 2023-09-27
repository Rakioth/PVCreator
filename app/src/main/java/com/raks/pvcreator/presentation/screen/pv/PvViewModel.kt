package com.raks.pvcreator.presentation.screen.pv

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raks.pvcreator.domain.model.ThemeIcon
import com.raks.pvcreator.domain.usecase.PvUseCases
import com.raks.pvcreator.domain.usecase.ThemeUseCases
import com.raks.pvcreator.domain.util.PvCreator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PvViewModel @Inject constructor(
    private val pvUseCases: PvUseCases,
) : ViewModel() {

    private val _state = mutableStateOf(PvState())
    val state: State<PvState> = _state

    private val _pvCard = mutableStateOf(
        PvCreator(
            card = 1,
            item = "000000110000",
            variant = null,
            wildcard = null,
            name = "",
        )
    )
    val pvCard: State<PvCreator> = _pvCard

    init {
        viewModelScope.launch {
            _state.value = state.value.copy(
                cards = pvUseCases.getCards(),
                items = pvUseCases.getItems(1),
                variants = pvUseCases.getVariants(1),
                wildcards = pvUseCases.getWildcards(1),
                barcode = pvUseCases.getBarcode(pvCard.value),
            )
        }
    }

    fun onEvent(event: PvEvent) {
        when (event) {
            is PvEvent.InputCard     -> {
                if (event.pickerOption.id !in 1..2)
                    viewModelScope.launch {
                        _state.value = state.value.copy(
                            items = pvUseCases.getItems(event.pickerOption.id),
                        )
                    }
                else
                    viewModelScope.launch {
                        _state.value = state.value.copy(
                            variants = pvUseCases.getVariants(event.pickerOption.id),
                            wildcards = pvUseCases.getWildcards(event.pickerOption.id),
                        )
                    }

                _pvCard.value = pvCard.value.copy(
                    card = event.pickerOption.id
                )
            }

            is PvEvent.InputItem     -> {
                if (event.pickerOption.id in 1..2)
                    viewModelScope.launch {
                        _state.value = state.value.copy(
                            variants = pvUseCases.getVariants(event.pickerOption.id),
                            wildcards = pvUseCases.getWildcards(event.pickerOption.id),
                        )
                    }

                _pvCard.value = pvCard.value.copy(
                    item = event.pickerOption.code!!
                )
            }

            is PvEvent.InputVariant  -> {
                _pvCard.value = pvCard.value.copy(
                    variant = event.pickerOption.code
                )
            }

            is PvEvent.InputWildcard -> {
                _pvCard.value = pvCard.value.copy(
                    wildcard = event.pickerOption.code
                )
            }

            is PvEvent.InputName     -> {
                _pvCard.value = pvCard.value.copy(
                    name = event.name
                )
            }
        }
    }


}