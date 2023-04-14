package com.raks.pvcreator.domain.model

import android.content.ComponentName
import android.content.Context

enum class ThemeIcon(
    private val key: String
) {

    DEFAULT("DefaultIcon"),
    LIGHT("LightIcon"),
    DARK("DarkIcon");

    private var componentName: ComponentName? = null

    fun getComponentName(context: Context): ComponentName {
        if (componentName == null)
            componentName = ComponentName(context.packageName, "com.raks.pvcreator.$key")
        return componentName!!
    }

}
