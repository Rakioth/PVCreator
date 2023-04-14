package com.raks.pvcreator.domain.model

data class PickerOption(
    val id:   Int,
    val ref:  String,
    val code: String?,
) {

    companion object {
        fun none() = PickerOption(0, "label_none", null)
    }

}
