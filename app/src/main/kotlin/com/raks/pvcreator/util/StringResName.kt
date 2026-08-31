package com.raks.pvcreator.util

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import com.raks.pvcreator.R

@SuppressLint("DiscouragedApi")
fun Context.getStringByName(name: String): String {
    val id = try {
        val idField = R.string::class.java.getDeclaredField(name)
        idField.getInt(idField)
    } catch (e: Exception) {
        resources.getIdentifier(name, "string", packageName)
    }

    return try {
        getString(id)
    } catch (e: Exception) {
        getString(R.string.label_resource)
    }
}

@Composable
@ReadOnlyComposable
fun stringResName(name: String): String = LocalContext.current.getStringByName(name)
