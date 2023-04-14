package com.raks.pvcreator.domain.util

data class PvCreator(
    val card:     Int,
    val item:     String,
    val variant:  String?,
    val wildcard: String?,
    val duration: String,
    val name:     String,
) {

    companion object {
        private const val ITEM_PAYLOAD       = "00001"
        private const val VARIANT_PAYLOAD    = "01010"
        private const val WILDCARD_PAYLOAD   = "01001"
        private const val WEATHER_PAYLOAD    = "10010"
        private const val DURATION_PAYLOAD   = "10011"
        private const val NAME_START_PAYLOAD = "00111"
        private const val NAME_END_PAYLOAD   = "00000"
        private const val EMPTY_PAYLOAD      = ""

        const val TYPE_PINATA                = 1
        const val TYPE_EGG                   = 2
        const val TYPE_WEATHER               = 21

        fun default() = PvCreator(1, "000000110000", null, null, "01001111", "")
    }

    val cardPayload:     Boolean
        get() = card !in setOf(TYPE_PINATA, TYPE_EGG, TYPE_WEATHER)

    val itemPayload:     String
        get() = toItemPayload()

    val variantPayload:  String
        get() = toVariantPayload()

    val wildcardPayload: String
        get() = toWildcardPayload()

    val durationPayload: String
        get() = toDurationPayload()

    val namePayload:     String
        get() = toNamePayload()

    private fun toItemPayload():     String = when (card) {
        TYPE_WEATHER -> "$WEATHER_PAYLOAD$item"
        else         -> "$ITEM_PAYLOAD$item"
    }

    private fun toVariantPayload():  String = when (card) {
        TYPE_PINATA, TYPE_EGG -> variant?.let { "$VARIANT_PAYLOAD$it" } ?: EMPTY_PAYLOAD
        else                  -> EMPTY_PAYLOAD
    }

    private fun toWildcardPayload(): String = when (card) {
        TYPE_PINATA, TYPE_EGG -> wildcard?.let { "$WILDCARD_PAYLOAD$it" } ?: EMPTY_PAYLOAD
        else                  -> EMPTY_PAYLOAD
    }

    private fun toDurationPayload(): String = when (card) {
        TYPE_WEATHER -> "$DURATION_PAYLOAD$duration"
        else         -> EMPTY_PAYLOAD
    }

    private fun toNamePayload():     String = when (card) {
        TYPE_PINATA -> nameToBits()
        else        -> EMPTY_PAYLOAD
    }

    private fun nameToBits(): String {
        if (name.isBlank())
            return EMPTY_PAYLOAD

        val bits = StringBuilder()
        name.codePoints()
            .forEach {
                bits.append(
                    (it + 1)
                        .toString(2)
                        .substring(2)
                )
            }

        return "$NAME_START_PAYLOAD$bits$NAME_END_PAYLOAD"
    }

}
