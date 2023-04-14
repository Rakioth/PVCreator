package com.raks.pvcreator.presentation.events

import com.raks.pvcreator.domain.model.PickerOption

sealed class PvEvent {
    data class InputCard(val pickerOption: PickerOption)     : PvEvent()
    data class InputItem(val pickerOption: PickerOption)     : PvEvent()
    data class InputVariant(val pickerOption: PickerOption)  : PvEvent()
    data class InputWildcard(val pickerOption: PickerOption) : PvEvent()
    data class InputDuration(val pickerOption: PickerOption) : PvEvent()
    data class InputName(val name: String)                   : PvEvent()
}
