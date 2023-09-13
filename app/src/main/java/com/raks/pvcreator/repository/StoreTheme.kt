//package com.raks.pvcreator.repository
//
//import androidx.datastore.core.DataStore
//import androidx.datastore.preferences.core.Preferences
//import androidx.datastore.preferences.core.booleanPreferencesKey
//import androidx.datastore.preferences.core.edit
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.map
//
//class StoreTheme(
//    private val dataStore: DataStore<Preferences>,
//) {
//
//    companion object {
//        private val THEME_ACTIVE_KEY = booleanPreferencesKey("theme_active")
//        private val THEME_DARK_KEY = booleanPreferencesKey("theme_dark")
//    }
//
//    val getTheme: Flow<Boolean> = dataStore.data
//        .map { preferences ->
//            preferences[THEME_DARK_KEY] ?: false
//        }
//
//    val getTemp: Flow<Boolean> = dataStore.data
//        .map { preferences ->
//            preferences[THEME_ACTIVE_KEY] ?: false
//        }
//
//    suspend fun saveTheme(value: Boolean) {
//        dataStore.edit { preferences ->
//            val temp = preferences[THEME_ACTIVE_KEY] ?: false
//
//            if (!temp)
//                preferences[THEME_ACTIVE_KEY] = true
//
//            preferences[THEME_DARK_KEY] = value
//        }
//    }
//
//}