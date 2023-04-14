package com.raks.pvcreator.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.raks.pvcreator.R
import com.raks.pvcreator.presentation.events.PvEvent
import com.raks.pvcreator.presentation.viewmodels.PvViewModel

@Composable
fun PvLayout(
    paddingValues: PaddingValues,
    viewModel:     PvViewModel   = hiltViewModel(),
) {
    val state = viewModel.state

    PvCard(
        paddingValues = paddingValues,
        textfields    = {
            PvTextField(
                label            = R.string.label_card,
                options          = state.cards,
                onScrollFinished = { viewModel.onEvent(PvEvent.InputCard(it)) },
            )
            PvTextField(
                label            = R.string.label_item,
                options          = state.items,
                onScrollFinished = { viewModel.onEvent(PvEvent.InputItem(it)) },
            )
            if (state.isWeatherType)
                PvTextField(
                    label            = R.string.label_duration,
                    options          = state.durations,
                    onScrollFinished = { viewModel.onEvent(PvEvent.InputDuration(it)) },
                )
            else
                PvTextField(
                    label            = R.string.label_variant,
                    options          = state.variants,
                    isVisible        = state.isEggType,
                    onScrollFinished = { viewModel.onEvent(PvEvent.InputVariant(it)) },
                )
            PvTextField(
                label            = R.string.label_wildcard,
                options          = state.wildcards,
                isVisible        = state.isEggType,
                onScrollFinished = { viewModel.onEvent(PvEvent.InputWildcard(it)) },
            )
            PvTextField(
                label            = R.string.label_name,
                isVisible        = state.isPinataType,
                isPicker         = false,
                onValueChange    = { viewModel.onEvent(PvEvent.InputName(it)) },
            )
        },
        barcode       = {
            PvBarcode(
                barcodePattern = state.barcode,
            )
        },
    )
}
