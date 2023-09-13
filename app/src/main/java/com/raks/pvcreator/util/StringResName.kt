//package com.raks.pvcreator.util
//
//import android.annotation.SuppressLint
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.stringResource
//import com.raks.pvcreator.R
//
//@SuppressLint("DiscouragedApi")
//fun stringResName(
//    ref: String,
//): String {
//
//    return try {
//        val idField = R.string::class.java.getDeclaredField(ref)
//        val id      = idField.getInt(idField)
//        stringResource(id)
//    } catch (e: Exception) {
//        val context = LocalContext.current
//        val id      = context.resources.getIdentifier(ref, "string", context.packageName)
//        stringResource(id)
//    }
//
//}