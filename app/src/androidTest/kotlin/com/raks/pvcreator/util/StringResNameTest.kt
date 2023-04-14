package com.raks.pvcreator.util

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.raks.pvcreator.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
@RunWith(AndroidJUnit4::class)
class StringResNameTest {

    @get:Rule
    val composeRule = createComposeRule()

    companion object {
        private       val VALID_RES_ID     = R.string.card_pinata
        private const val VALID_RES_NAME   = "card_pinata"
        private       val INVALID_RES_ID   = R.string.label_resource
        private const val INVALID_RES_NAME = "non_existent_resource"
    }

    @Test
    fun when_stringResName_is_called_with_valid_name_Expect_valid_resource() = composeRule.setContent {
        val stringRes         = stringResName(VALID_RES_NAME)
        val expectedStringRes = stringResource(VALID_RES_ID)
        assertThat(stringRes).isEqualTo(expectedStringRes)
    }

    @Test
    fun when_stringResName_is_called_with_invalid_name_Expect_invalid_resource() = composeRule.setContent {
        val stringRes         = stringResName(INVALID_RES_NAME)
        val expectedStringRes = stringResource(INVALID_RES_ID)
        assertThat(stringRes).isEqualTo(expectedStringRes)
    }

}
