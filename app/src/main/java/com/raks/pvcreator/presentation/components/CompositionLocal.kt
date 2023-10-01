package com.raks.pvcreator.presentation.components

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.IntSize
import androidx.navigation.NavHostController

val PVCardSize = compositionLocalOf { IntSize.Zero }
val LocalTheme = compositionLocalOf { false }
val LocalNavController = compositionLocalOf<NavHostController> { error("No LocalNavController provided") }