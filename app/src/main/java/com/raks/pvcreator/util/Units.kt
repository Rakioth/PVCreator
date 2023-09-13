package com.raks.pvcreator.util

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType

var deviceSize   = mutableStateOf(IntSize.Zero)

var deviceWidth  = 0f
var deviceHeight = 0f

var density      = 0f
var scaleDensity = 0f

inline val Number.PxToSp get() = this.toFloat() / scaleDensity
inline val Number.PxToDp get() = this.toFloat() / density

fun initSize(
    context: Context,
    size:    IntSize,
) {
    deviceSize.value = size
    deviceWidth  = deviceSize.value.width.toFloat()
    deviceHeight = deviceSize.value.height.toFloat()
    density      = context.resources.displayMetrics.density
    scaleDensity = context.resources.displayMetrics.scaledDensity
}

inline val Number.pw: Dp
    get() = Dp(value = ((this.toFloat() / 100) * deviceWidth).PxToDp)

inline val Number.ph: Dp
    get() = Dp(value = ((this.toFloat() / 100) * deviceHeight).PxToDp)

inline val Number.sw: TextUnit
    get() = TextUnit(((this.toFloat() / 100) * deviceWidth).PxToSp, TextUnitType.Sp)

inline val Number.sh: TextUnit
    get() = TextUnit(((this.toFloat() / 100) * deviceHeight).PxToSp, TextUnitType.Sp)