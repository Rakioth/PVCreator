package com.raks.pvcreator.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raks.pvcreator.domain.model.PickerOption
import com.raks.pvcreator.domain.usecase.PvUseCases
import com.raks.pvcreator.domain.util.PvCreator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pvUseCases: PvUseCases
) : ViewModel() {

    var state by mutableStateOf(MainState())
        private set

    val pvCard = MutableStateFlow(
        PvCreator(
            card = 1,
            item = "000000110000",
            variant = null,
            wildcard = null,
            name = "",
        )
    )


    init {
        onUiReady()
//        viewModelScope.launch {
//            pvCard.collect {
//                state = state.copy(
//                    cards = pvUseCases.getCards(),
////                    items = pvUseCases.getItems(it.optionCard.id),
////                    variants = pvUseCases.getVariants(it.optionItem.id),
////                    wildcards = pvUseCases.getWildcards(it.optionItem.id),
//                    barcode = pvUseCases.getBarcode(it)
//                )
//            }
//        }
    }


    private fun notifyCardChange(item: PickerOption) {
        viewModelScope.launch {
            state = state.copy(items = pvUseCases.getItems(item.id))
        }
    }

    private fun notifyItemChange(item: PickerOption) {
        viewModelScope.launch {
            state =
                state.copy(
                    variants = pvUseCases.getVariants(item.id),
                    wildcards = pvUseCases.getWildcards(item.id)
                )
        }
    }

    private fun onUiReady() {
        viewModelScope.launch {
            state = state.copy(
                cards = pvUseCases.getCards(),
                items = pvUseCases.getItems(1),
                variants = pvUseCases.getVariants(1),
                wildcards = pvUseCases.getWildcards(1),
            )
        }
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.InputCard     -> {
//                pvCard.update {
//                    it.copy(optionCard = event.pickerOption)
//                }
            }

            is MainEvent.InputItem     -> {
//                pvCard.update {
//                    it.copy(optionItem = event.pickerOption)
//                }
            }

            is MainEvent.InputVariant  -> {
//                pvCard.update {
//                    it.copy(optionVariant = event.pickerOption)
//                }
            }

            is MainEvent.InputWildcard -> {
//                pvCard.update {
//                    it.copy(optionWildcard = event.pickerOption)
//                }
            }

            is MainEvent.InputName     -> {
                pvCard.update {
                    it.copy(name = event.name)
                }
            }
        }
    }


}