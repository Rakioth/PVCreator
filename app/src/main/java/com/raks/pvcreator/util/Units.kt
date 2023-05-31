package com.raks.pvcreator.util

import android.content.Context
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType

var DeviceWidth = 0f
var DeviceHeight = 0f

var Density = 0f
var ScaleDensity = 0f

inline val Number.PxToSp get() = this.toFloat() / ScaleDensity
inline val Number.PxToDp get() = this.toFloat() / Density

fun initSize(context: Context, size: IntSize) {
    DeviceWidth = size.width.toFloat()
    DeviceHeight = size.height.toFloat()
    Density = context.resources.displayMetrics.density
    ScaleDensity = context.resources.displayMetrics.scaledDensity
    println(DeviceWidth)
    println(DeviceHeight)
    println(Density)
    println(ScaleDensity)
}

inline val Number.pw: Dp
    get() = Dp(value = ((this.toFloat() / 100) * DeviceWidth).PxToDp)

inline val Number.ph: Dp
    get() = Dp(value = ((this.toFloat() / 100) * DeviceHeight).PxToDp)

inline val Number.sw: TextUnit
    get() = TextUnit(((this.toFloat() / 100) * DeviceWidth).PxToSp, TextUnitType.Sp)

inline val Number.sh: TextUnit
    get() = TextUnit(((this.toFloat() / 100) * DeviceHeight).PxToSp, TextUnitType.Sp)