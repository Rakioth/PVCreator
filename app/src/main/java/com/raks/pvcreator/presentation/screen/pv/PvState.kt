package com.raks.pvcreator.presentation.screen.pv

import com.raks.pvcreator.domain.model.PickerOption

data class PvState(
    val cards:     List<PickerOption> = emptyList(),
    val items:     List<PickerOption> = emptyList(),
    val variants:  List<PickerOption> = emptyList(),
    val wildcards: List<PickerOption> = emptyList(),
    val barcode:   List<String>       = emptyList(),
)