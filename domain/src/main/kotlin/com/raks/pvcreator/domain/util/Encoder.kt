package com.raks.pvcreator.domain.util

import kotlin.experimental.xor

object Encoder {

    private const val START_PAYLOAD = "0000000011011000000"
    private const val END_PAYLOAD   = "000000000"
    private const val BAR_LENGTH    = 113
    private const val FIRST_BITS    = 60
    private const val LAST_BITS     = 4

    private val shuffle = arrayOf(
        arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59),
        arrayOf(0, 30, 1, 31, 2, 32, 3, 33, 4, 34, 5, 35, 6, 36, 7, 37, 8, 38, 9, 39, 10, 40, 11, 41, 12, 42, 13, 43, 14, 44, 15, 45, 16, 46, 17, 47, 18, 48, 19, 49, 20, 50, 21, 51, 22, 52, 23, 53, 24, 54, 25, 55, 26, 56, 27, 57, 28, 58, 29, 59),
        arrayOf(0, 20, 40, 1, 21, 41, 2, 22, 42, 3, 23, 43, 4, 24, 44, 5, 25, 45, 6, 26, 46, 7, 27, 47, 8, 28, 48, 9, 29, 49, 10, 30, 50, 11, 31, 51, 12, 32, 52, 13, 33, 53, 14, 34, 54, 15, 35, 55, 16, 36, 56, 17, 37, 57, 18, 38, 58, 19, 39, 59),
        arrayOf(0, 12, 24, 36, 48, 1, 13, 25, 37, 49, 2, 14, 26, 38, 50, 3, 15, 27, 39, 51, 4, 16, 28, 40, 52, 5, 17, 29, 41, 53, 6, 18, 30, 42, 54, 7, 19, 31, 43, 55, 8, 20, 32, 44, 56, 9, 21, 33, 45, 57, 10, 22, 34, 46, 58, 11, 23, 35, 47, 59),
        arrayOf(0, 9, 18, 27, 36, 44, 52, 1, 10, 19, 28, 37, 45, 53, 2, 11, 20, 29, 38, 46, 54, 3, 12, 21, 30, 39, 47, 55, 4, 13, 22, 31, 40, 48, 56, 5, 14, 23, 32, 41, 49, 57, 6, 15, 24, 33, 42, 50, 58, 7, 16, 25, 34, 43, 51, 59, 8, 17, 26, 35),
        arrayOf(0, 7, 14, 21, 28, 35, 42, 48, 54, 1, 8, 15, 22, 29, 36, 43, 49, 55, 2, 9, 16, 23, 30, 37, 44, 50, 56, 3, 10, 17, 24, 31, 38, 45, 51, 57, 4, 11, 18, 25, 32, 39, 46, 52, 58, 5, 12, 19, 26, 33, 40, 47, 53, 59, 6, 13, 20, 27, 34, 41),
        arrayOf(0, 6, 12, 18, 24, 30, 35, 40, 45, 50, 55, 1, 7, 13, 19, 25, 31, 36, 41, 46, 51, 56, 2, 8, 14, 20, 26, 32, 37, 42, 47, 52, 57, 3, 9, 15, 21, 27, 33, 38, 43, 48, 53, 58, 4, 10, 16, 22, 28, 34, 39, 44, 49, 54, 59, 5, 11, 17, 23, 29),
        arrayOf(0, 5, 10, 15, 20, 25, 30, 35, 40, 44, 48, 52, 56, 1, 6, 11, 16, 21, 26, 31, 36, 41, 45, 49, 53, 57, 2, 7, 12, 17, 22, 27, 32, 37, 42, 46, 50, 54, 58, 3, 8, 13, 18, 23, 28, 33, 38, 43, 47, 51, 55, 59, 4, 9, 14, 19, 24, 29, 34, 39),
        arrayOf(0, 4, 8, 12, 16, 20, 24, 28, 32, 36, 39, 42, 45, 48, 51, 54, 57, 1, 5, 9, 13, 17, 21, 25, 29, 33, 37, 40, 43, 46, 49, 52, 55, 58, 2, 6, 10, 14, 18, 22, 26, 30, 34, 38, 41, 44, 47, 50, 53, 56, 59, 3, 7, 11, 15, 19, 23, 27, 31, 35),
        arrayOf(0, 4, 8, 12, 15, 18, 21, 24, 27, 30, 33, 36, 39, 42, 45, 48, 51, 54, 57, 1, 5, 9, 13, 16, 19, 22, 25, 28, 31, 34, 37, 40, 43, 46, 49, 52, 55, 58, 2, 6, 10, 14, 17, 20, 23, 26, 29, 32, 35, 38, 41, 44, 47, 50, 53, 56, 59, 3, 7, 11),
        arrayOf(0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 54, 56, 58, 59, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57),
        arrayOf(0, 3, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 54, 56, 58, 1, 4, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 2, 5),
        arrayOf(0, 3, 6, 9, 12, 15, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 54, 56, 58, 1, 4, 7, 10, 13, 16, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 2, 5, 8, 11, 14, 17),
        arrayOf(0, 3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36, 39, 42, 44, 46, 48, 50, 52, 54, 56, 58, 1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34, 37, 40, 43, 45, 47, 49, 51, 53, 55, 57, 59, 2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35, 38, 41),
        arrayOf(0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41),
        arrayOf(0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45),
    )
    private val negate  = arrayOf(
        "878f0b496878f0b0",
        "9607cf2b59607cf0",
        "a59e03cd2a59e030",
        "b416c7af1b416c70",
        "c3ad1a41ec3ad1b0",
        "d225de23dd225df0",
        "e1bc12c5ae1bc130",
        "f034d6a79f034d70",
        "f0b496878f0b4970",
        "e13c52e5be13c530",
        "a59e03cd2a59e030",
        "b416c7af1b416c70",
        "c32d5a61fc32d5b0",
        "d2a59e03cd2a59f0",
        "878f0b496878f0b0",
        "9607cf2b59607cf0",
    )

    fun getBarcode(pvCreator: PvCreator): List<String> {
        if (pvCreator.cardPayload)
            return pvCreator.item
                .chunked(BAR_LENGTH)

        val barPattern = mapOf(
            '0' to "1110010",
            '1' to "1100110",
            '2' to "1101100",
            '3' to "1000010",
            '4' to "1011100",
            '5' to "1001110",
            '6' to "1010000",
            '7' to "1000100",
            '8' to "1001000",
            '9' to "1110100",
            'a' to "1011000",
            'b' to "1001100",
            'c' to "1100100",
            'd' to "1101000",
            'e' to "1100010",
            'f' to "1000110",
        )

        val barcode = StringBuilder()
        encodeData(pvCreator)
            .chunked(barPattern.size)
            .forEach { c ->
                c.forEach { barcode.append(barPattern[it]) }
                barcode.append("1")
            }

        return barcode
            .toString()
            .reversed()
            .chunked(BAR_LENGTH)
    }

    private fun encodeData(pvCreator: PvCreator): String {
        val sb = StringBuilder()

        sb.append(START_PAYLOAD)
          .append(pvCreator.durationPayload)
          .append(pvCreator.itemPayload)
          .append(pvCreator.namePayload)
          .append(pvCreator.wildcardPayload)
          .append(pvCreator.variantPayload)
          .append(END_PAYLOAD)

        val encodedData = sb.toString()
        val padding     = if (encodedData.length % FIRST_BITS != 0) FIRST_BITS - (encodedData.length % FIRST_BITS) else 0

        return encodedData
            .padEnd(encodedData.length + padding, '0')
            .chunked(FIRST_BITS)
            .joinToString("") { obfuscate(it) }
    }

    private fun obfuscate(row: String): String {
        var check            = checkDigit(row)
        val index            = check?.toInt(16)
        val bitsXor          = arrayOfNulls<String>(FIRST_BITS)
        val obfuscationTable = mapOf(
            "7" to '0',
            "6" to '1',
            "5" to '2',
            "4" to '3',
            "3" to '4',
            "2" to '5',
            "1" to '6',
            "0" to '7',
            "e" to '8',
            "f" to '9',
            "a" to 'a',
            "b" to 'b',
            "c" to 'c',
            "d" to 'd',
            "9" to 'e',
            "8" to 'f',
        )

        for (i in 0..59)
            bitsXor[shuffle[index!!][i]] = row[i].toString()

        check = obfuscationTable[check]
            ?.digitToInt(16)
            ?.toString(2)
            ?.padStart(LAST_BITS, '0')

        val heXor = "${bitsXor.joinToString("")}$check"
            .toBigInteger(2)
            .toString(16)
            .padStart(16, '0')

        val bytesXor    = heXor.parseHex()
        val bytesNegate = negate[index!!].parseHex()

        for (i in bytesXor.indices)
            bytesXor[i] = bytesXor[i] xor bytesNegate[i]

        val obfuscatedRow = StringBuilder()
        bytesXor.formatHex()
                .forEach { c ->
                    obfuscatedRow.append(obfuscationTable.entries.find { it.value == c }?.key)
                }

        return obfuscatedRow.toString()
    }

    private fun checkDigit(row: String): String? {
        var checksum       = 0
        var weight         = 3
        val obfuscationSet = mapOf(
             0 to "0",
             1 to "1",
             2 to "2",
             3 to "3",
             4 to "4",
             5 to "5",
             6 to "6",
             7 to "7",
             8 to "8",
             9 to "9",
            10 to "d",
            11 to "c",
            12 to "b",
            13 to "a",
            14 to "f",
            15 to "e",
        )
        val hex            = row
            .toBigInteger(2)
            .toString(16)
            .padStart(15, '0')

        hex.forEach {
            checksum += it.digitToInt(16) * weight
            weight    = 4 - weight
        }

        obfuscationSet.keys.forEach {
            if ((checksum + it) % 16 == 0)
                return obfuscationSet[it]
        }

        return null
    }

}
