package com.raks.pvcreator.util

import android.database.sqlite.SQLiteDatabase
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertWithMessage
import com.raks.pvcreator.R
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

@SmallTest
@RunWith(AndroidJUnit4::class)
class LabelResourcesTest {

    companion object {
        private const val DATABASE_ASSET_PATH = "database/pvcreator.db"
        private       val LABEL_TABLES        = listOf("cards", "items", "variants", "wildcards", "durations")
    }

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun when_every_label_key_in_the_database_is_resolved_Expect_no_missing_resources() {
        val fallback   = context.getString(R.string.label_resource)
        val unresolved = mutableListOf<String>()

        SQLiteDatabase.openDatabase(copyDatabaseFromAssets().path, null, SQLiteDatabase.OPEN_READONLY).use { database ->
            LABEL_TABLES.forEach { table ->
                database.rawQuery("SELECT ref FROM $table", null).use { cursor ->
                    while (cursor.moveToNext()) {
                        val ref = cursor.getString(0)
                        if (context.getStringByName(ref) == fallback) unresolved += "$table/$ref"
                    }
                }
            }
        }

        assertWithMessage("Label keys without a matching string resource").that(unresolved).isEmpty()
    }

    private fun copyDatabaseFromAssets(): File {
        val databaseFile = File(context.cacheDir, "label-resources-test.db")
        context.assets.open(DATABASE_ASSET_PATH).use { input ->
            databaseFile.outputStream().use { output -> input.copyTo(output) }
        }
        return databaseFile
    }

}
