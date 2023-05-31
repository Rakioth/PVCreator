package com.raks.pvcreator.ui.main

import com.raks.pvcreator.domain.model.PickerOption

data class MainState(
    val cards:     List<PickerOption> = emptyList(),
    val items:     List<PickerOption> = emptyList(),
    val variants:  List<PickerOption> = emptyList(),
    val wildcards: List<PickerOption> = emptyList(),
    val barcode:   List<String>       = emptyList(),
)