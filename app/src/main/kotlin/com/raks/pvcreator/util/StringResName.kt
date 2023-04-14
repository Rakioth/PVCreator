package com.raks.pvcreator.util

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import com.raks.pvcreator.R

@SuppressLint("DiscouragedApi")
@Composable
@ReadOnlyComposable
fun stringResName(name: String): String {
    val context = LocalContext.current

    val id = try {
        val idField = R.string::class.java.getDeclaredField(name)
        idField.getInt(idField)
    } catch (e: Exception) {
        context.resources.getIdentifier(name, "string", context.packageName)
    }

    val resource = try {
        context.getString(id)
    } catch (e: Exception) {
        context.getString(R.string.label_resource)
    }

    return resource
}
