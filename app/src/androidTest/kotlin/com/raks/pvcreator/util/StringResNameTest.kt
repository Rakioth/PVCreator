package com.raks.pvcreator.util

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.raks.pvcreator.R
import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
@RunWith(AndroidJUnit4::class)
class StringResNameTest {

    companion object {
        private       val VALID_RES_ID     = R.string.card_pinata
        private const val VALID_RES_NAME   = "card_pinata"
        private       val INVALID_RES_ID   = R.string.label_resource
        private const val INVALID_RES_NAME = "non_existent_resource"
    }

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun when_getStringByName_is_called_with_valid_name_Expect_valid_resource() {
        val stringRes         = context.getStringByName(VALID_RES_NAME)
        val expectedStringRes = context.getString(VALID_RES_ID)
        assertThat(stringRes).isEqualTo(expectedStringRes)
    }

    @Test
    fun when_getStringByName_is_called_with_invalid_name_Expect_invalid_resource() {
        val stringRes         = context.getStringByName(INVALID_RES_NAME)
        val expectedStringRes = context.getString(INVALID_RES_ID)
        assertThat(stringRes).isEqualTo(expectedStringRes)
    }

}
