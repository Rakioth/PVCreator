package com.raks.pvcreator.ui.main

import com.raks.pvcreator.domain.model.PickerOption

sealed class MainEvent {
    data class InputCard(val pickerOption: PickerOption) : MainEvent()
    data class InputItem(val pickerOption: PickerOption) : MainEvent()
    data class InputVariant(val pickerOption: PickerOption) : MainEvent()
    data class InputWildcard(val pickerOption: PickerOption) : MainEvent()
    data class InputName(val name: String) : MainEvent()
}