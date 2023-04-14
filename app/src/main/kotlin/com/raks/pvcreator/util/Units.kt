package com.raks.pvcreator.util

import android.content.Context
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType

class Units(
    size:    IntSize,
    context: Context,
) {

    private val density = context.resources.displayMetrics.density
    private val width   = size.width.toFloat()
    private val height  = size.height.toFloat()

    fun pw(number: Number): Dp       =
        Dp(value = ((number.toFloat() / 100) * width) / density)

    fun ph(number: Number): Dp       =
        Dp(value = ((number.toFloat() / 100) * height) / density)

    fun sw(number: Number): TextUnit =
        TextUnit(
            value = ((number.toFloat() / 100) * width) / density,
            type  = TextUnitType.Sp,
        )

    fun sh(number: Number): TextUnit =
        TextUnit(
            value = ((number.toFloat() / 100) * height) / density,
            type  = TextUnitType.Sp,
        )

}
