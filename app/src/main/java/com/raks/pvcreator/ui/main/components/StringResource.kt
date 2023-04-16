package com.raks.pvcreator.ui.main.components

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource

@SuppressLint("DiscouragedApi")
@Composable
fun asString(ref: String): String {
    val resourceId = LocalContext.current.resources.getIdentifier(ref, "string", LocalContext.current.packageName)
    return stringResource(resourceId)
}
