package com.raks.pvcreator.domain.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class EncoderTest {

    companion object {
        private const val BARCODE_ROWS    = 4
        private const val BARCODE_COLUMNS = 113
    }

    @Test
    fun when_getBarcode_is_called_Expect_valid_pinata_barcode() {
        val pvCreator = PvCreator(PvCreator.TYPE_PINATA, "000000110000", "0001", "01", "", "Rick")
        val barcode   = Encoder.getBarcode(pvCreator)

        assertThat(barcode).isNotEmpty()
        assertThat(barcode.size).isAtMost(BARCODE_ROWS)

        barcode.forEach {
            assertThat(it).isNotEmpty()
            assertThat(it).hasLength(BARCODE_COLUMNS)
            assertThat(it).matches("[01]+")
        }
    }

    @Test
    fun when_getBarcode_is_called_Expect_valid_egg_barcode() {
        val pvCreator = PvCreator(PvCreator.TYPE_EGG, "000100100100", "0001", "01", "", "")
        val barcode   = Encoder.getBarcode(pvCreator)

        assertThat(barcode).isNotEmpty()
        assertThat(barcode.size).isAtMost(BARCODE_ROWS)

        barcode.forEach {
            assertThat(it).isNotEmpty()
            assertThat(it).hasLength(BARCODE_COLUMNS)
            assertThat(it).matches("[01]+")
        }
    }

    @Test
    fun when_getBarcode_is_called_Expect_valid_weather_barcode() {
        val pvCreator = PvCreator(PvCreator.TYPE_WEATHER, "000", null, null, "01001111", "")
        val barcode   = Encoder.getBarcode(pvCreator)

        assertThat(barcode).isNotEmpty()
        assertThat(barcode.size).isAtMost(BARCODE_ROWS)

        barcode.forEach {
            assertThat(it).isNotEmpty()
            assertThat(it).hasLength(BARCODE_COLUMNS)
            assertThat(it).matches("[01]+")
        }
    }

}
