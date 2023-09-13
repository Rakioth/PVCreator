package com.raks.pvcreator.repository

import android.content.ComponentName
import android.content.Context

enum class Icon(
    private val key: String
) {

    DEFAULT("DefaultIcon"),
    LIGHT("LightIcon"),
    DARK("DarkIcon");

    private var componentName: ComponentName? = null

    fun getComponentName(context: Context): ComponentName {
        if (componentName == null)
            componentName = ComponentName(context.packageName, "com.raks.pvcreator.ui.$key")
        return componentName!!
    }

}