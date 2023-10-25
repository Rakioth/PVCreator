package com.raks.pvcreator.util

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.raks.pvcreator.R

@SuppressLint("DiscouragedApi")
@Composable
@ReadOnlyComposable
fun stringResName(name: String): String {
    val id = try {
        val idField = R.string::class.java.getDeclaredField(name)
        idField.getInt(idField)
    } catch (e: Exception) {
        val context = LocalContext.current
        context.resources.getIdentifier(name, "string", context.packageName)
    }

    return stringResource(id)
}