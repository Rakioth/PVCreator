package com.raks.pvcreator.domain.util

data class PvCreator(
    var card:     Int,
    var item:     String,
    var variant:  String?,
    var wildcard: String?,
    var name:     String?,
) {

    companion object {
        private const val ITEM_PAYLOAD       = "00001"
        private const val VARIANT_PAYLOAD    = "01010"
        private const val WILDCARD_PAYLOAD   = "01001"
        private const val NAME_START_PAYLOAD = "00111"
        private const val NAME_END_PAYLOAD   = "00000"
    }

    init {
        item     = item.let          { "$ITEM_PAYLOAD$it" }
        variant  = variant?.let      { "$VARIANT_PAYLOAD$it" } ?: ""
        wildcard = wildcard?.let     { "$WILDCARD_PAYLOAD$it" } ?: ""
        name     = toBits(name)?.let { "$NAME_START_PAYLOAD$it$NAME_END_PAYLOAD" } ?: ""
    }

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