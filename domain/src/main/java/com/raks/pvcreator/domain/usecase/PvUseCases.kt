package com.raks.pvcreator.domain.usecase

data class PvUseCases(
    val getCards:     GetCards,
    val getItems:     GetItems,
    val getVariants:  GetVariants,
    val getWildcards: GetWildcards,
)