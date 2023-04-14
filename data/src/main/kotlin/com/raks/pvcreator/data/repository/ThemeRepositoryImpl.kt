package com.raks.pvcreator.data.repository

import android.content.Context
import android.content.pm.PackageManager
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.raks.pvcreator.domain.model.ThemeConfig
import com.raks.pvcreator.domain.model.ThemeIcon
import com.raks.pvcreator.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ThemeRepositoryImpl(
    private val context: Context
) : ThemeRepository {

    companion object {
        private val THEME_ACTIVE_KEY = booleanPreferencesKey("theme_active")
        private val THEME_ICON_KEY   = stringPreferencesKey("theme_icon")
    }

    override val themeConfig: Flow<ThemeConfig>
        get() = context.dataStore.data
            .catch {
                if (it is IOException)
                    emit(emptyPreferences())
                else throw it
            }
            .map {
                val isThemeActive = it[THEME_ACTIVE_KEY] ?: false
                val themeIcon     = it[THEME_ICON_KEY] ?: ThemeIcon.DEFAULT.name

                ThemeConfig(
                    isThemeActive,
                    ThemeIcon.valueOf(themeIcon),
                )
            }

    override suspend fun setTheme(themeIcon: ThemeIcon) {
        context.dataStore.edit {
            val isThemeActive = it[THEME_ACTIVE_KEY] ?: false

            if (!isThemeActive)
                it[THEME_ACTIVE_KEY] = true

            it[THEME_ICON_KEY] = themeIcon.name
        }

        for (icon in ThemeIcon.entries)
            context.packageManager.setComponentEnabledSetting(
                icon.getComponentName(context),
                when (icon) {
                    themeIcon         -> PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                    ThemeIcon.DEFAULT -> PackageManager.COMPONENT_ENABLED_STATE_DISABLED
                    else              -> PackageManager.COMPONENT_ENABLED_STATE_DEFAULT
                },
                PackageManager.DONT_KILL_APP
            )
    }

}
