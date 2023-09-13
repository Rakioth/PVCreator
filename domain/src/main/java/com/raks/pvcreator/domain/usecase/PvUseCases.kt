package com.raks.pvcreator.domain.usecase

import com.raks.pvcreator.domain.usecase.pv.*

data class PvUseCases(
    val getCards:     GetCards,
    val getItems:     GetItems,
    val getVariants:  GetVariants,
    val getWildcards: GetWildcards,
    val getBarcode:   GetBarcode,
)