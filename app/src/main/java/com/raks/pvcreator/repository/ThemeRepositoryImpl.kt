package com.raks.pvcreator.repository

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.raks.pvcreator.domain.model.ThemeConfig
import com.raks.pvcreator.domain.repository.ThemeRepository
import com.raks.pvcreator.presentation.screen.pv.ThemeState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ThemeRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
    private val iconManager: IconManager,
) : ThemeRepository {

    companion object {
        private val THEME_ACTIVE_KEY = booleanPreferencesKey("theme_active")
        private val THEME_DARK_KEY = booleanPreferencesKey("theme_dark")
    }

    private val scope = CoroutineScope(Main)

    init {
        dataStore.data
            .map {
                _themeConfig.value = themeConfig.value.copy(
                    isThemeActive = it[THEME_ACTIVE_KEY] ?: false,
                    isThemeDark = it[THEME_DARK_KEY] ?: false,
                )
            }
    }

    private val _themeConfig = mutableStateOf(
        ThemeConfig(
            isThemeActive = false,
            isThemeDark = false,
        )
    )

    override var themeConfig: State<ThemeConfig> = _themeConfig


    //    override val themeConfig: Flow<ThemeConfig>
//        get() = dataStore.data
//            .catch {
//                if (it is IOException)
//                    emit(emptyPreferences())
//                else
//                    throw it
//            }
//            .map {
//                val isThemeActive = it[THEME_ACTIVE_KEY] ?: false
//                val isThemeDark = it[THEME_DARK_KEY] ?: false
//
//                ThemeConfig(
//                    isThemeActive,
//                    isThemeDark,
//                )
//            }

    override suspend fun changeTheme(value: Boolean) {
        dataStore.edit {
            if (it[THEME_ACTIVE_KEY] != true)
                it[THEME_ACTIVE_KEY] = true

            it[THEME_DARK_KEY] = value
        }
    }

}