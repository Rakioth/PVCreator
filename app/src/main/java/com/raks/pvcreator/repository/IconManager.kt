package com.raks.pvcreator.repository

import android.content.Context
import android.content.pm.PackageManager

class IconManager(
    private val context: Context
) {

    fun setIcon(icon: Icon) {
        val pm = context.packageManager

        for (i in Icon.values())
            pm.setComponentEnabledSetting(
                i.getComponentName(context),
                if (i === icon)
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                else
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
    }

}