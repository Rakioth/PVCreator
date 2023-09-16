package com.raks.pvcreator.domain.util

data class PvCreator(
    val card:     Int,
    val item:     String,
    val variant:  String?,
    val wildcard: String?,
    val name:     String?,
) {

    companion object {
        private const val ITEM_PAYLOAD       = "00001"
        private const val VARIANT_PAYLOAD    = "01010"
        private const val WILDCARD_PAYLOAD   = "01001"
        private const val NAME_START_PAYLOAD = "00111"
        private const val NAME_END_PAYLOAD   = "00000"
    }

    val cardPayload:     Boolean
        get() = card !in 1..2

    val itemPayload:     String
        get() = item.let          { "$ITEM_PAYLOAD$it" }

    val variantPayload:  String
        get() = variant?.let      { "$VARIANT_PAYLOAD$it" } ?: ""

    val wildcardPayload: String
        get() = wildcard?.let     { "$WILDCARD_PAYLOAD$it" } ?: ""

    val namePayload:     String
        get() = toBits(name)?.let { "$NAME_START_PAYLOAD$it$NAME_END_PAYLOAD" } ?: ""

    private fun toBits(name: String?): String? {
        if (name.isNullOrBlank()) return null

        val bits = StringBuilder()
        name.codePoints()
            .forEach {
                bits.append(
                    (it + 1)
                        .toString(2)
                        .substring(2)
                )
            }

        return bits.toString()
    }

}