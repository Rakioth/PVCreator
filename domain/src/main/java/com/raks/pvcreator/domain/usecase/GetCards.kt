package com.raks.pvcreator.domain.usecase

import com.raks.pvcreator.domain.model.PickerOption
import com.raks.pvcreator.domain.repository.PvRepository

class GetCards(
    private val repository: PvRepository
) {

    suspend operator fun invoke(): List<PickerOption> =
        repository.getCards()

}