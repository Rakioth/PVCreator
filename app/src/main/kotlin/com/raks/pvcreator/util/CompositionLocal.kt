package com.raks.pvcreator.util

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.IntSize

val LocalTheme    = compositionLocalOf<Boolean> { error("No LocalTheme provided")    }
val LocalCardSize = compositionLocalOf<IntSize> { error("No LocalCardSize provided") }
