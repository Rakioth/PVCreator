package com.raks.pvcreator.data.repository

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.raks.pvcreator.domain.model.ThemeConfig
import com.raks.pvcreator.domain.model.ThemeIcon
import com.raks.pvcreator.domain.repository.ThemeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

@ExperimentalCoroutinesApi
@SmallTest
@RunWith(AndroidJUnit4::class)
class ThemeRepositoryTest {

    companion object {
        private val INITIAL_SETTINGS  = ThemeConfig(
            false,
            ThemeIcon.DEFAULT,
        )
        private val MODIFIED_SETTINGS = ThemeConfig(
            true,
            ThemeIcon.DARK,
        )
    }

    private val testContext    = InstrumentationRegistry.getInstrumentation().targetContext
    private val testDispatcher = StandardTestDispatcher()
    private val testScope      = TestScope(testDispatcher)

    private val repository: ThemeRepository = ThemeRepositoryImpl(testContext)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        PreferenceDataStoreFactory.create(
            scope       = testScope,
            produceFile = { testContext.preferencesDataStoreFile("settings_test") },
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        File(testContext.filesDir, "datastore").deleteRecursively()
        testScope.cancel()
    }

    @Test
    fun when_themeConfig_is_first_called_Expect_initial_settings() = testScope.runTest {
        repository.themeConfig.first().let {
            assertThat(it).isEqualTo(INITIAL_SETTINGS)
        }
    }

    @Test
    fun when_setTheme_is_called_Expect_modified_settings() = testScope.runTest {
        try { repository.setTheme(MODIFIED_SETTINGS.themeIcon) }
        catch (_: Exception) { }

        repository.themeConfig.first().let {
            assertThat(it).isEqualTo(MODIFIED_SETTINGS)
        }
    }

}
